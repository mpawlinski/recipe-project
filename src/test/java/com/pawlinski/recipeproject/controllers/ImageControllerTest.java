package com.pawlinski.recipeproject.controllers;

import com.pawlinski.recipeproject.commands.RecipeCommand;
import com.pawlinski.recipeproject.services.ImageService;
import com.pawlinski.recipeproject.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    ImageController imageController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageController = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void showUploadImageForm() throws Exception{
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        //when
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        //then
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/imageuploadform"));

    }

    @Test
    public void handleUploadImagePost() throws Exception{
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt",
                                                            "text/plain", "recipe project".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("location", "/recipe/1/show"));

        verify(imageService, times(1)).saveImageFile(any(), any());
    }

    @Test
    public void renderImageFromDb() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        String s = "fake image";
        Byte[] imageInBytes = new Byte[s.getBytes().length];
        recipeCommand.setImage(imageInBytes);

        int i=0;
        for(Byte b : s.getBytes()) {
            imageInBytes[i++] = b;
        }

        //when
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();

        //then
        assertEquals(s.getBytes().length, responseBytes.length);

    }

    @Test
    public void testGetImageNumberFormatException() throws Exception {

        mockMvc.perform(get("/recipe/wrongInput/recipeimage"))
                .andExpect(view().name("recipe/400error"))
                .andExpect(status().isBadRequest());
    }
}