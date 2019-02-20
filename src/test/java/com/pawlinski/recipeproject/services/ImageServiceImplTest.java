package com.pawlinski.recipeproject.services;

import com.pawlinski.recipeproject.model.Recipe;
import com.pawlinski.recipeproject.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    ImageService imageService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    public void saveImageFile() throws Exception{
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        MultipartFile multipartFile = new MockMultipartFile("filename", "testing.txt", "text/plain", "testImageFile".getBytes());

        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //when
        imageService.saveImageFile(1L, multipartFile);

        //then
        verify(recipeRepository, times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();
//        verify(recipeRepository, times(1)).save(any(Recipe.class));
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
    }
}