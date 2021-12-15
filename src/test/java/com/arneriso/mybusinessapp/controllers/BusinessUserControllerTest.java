package com.arneriso.mybusinessapp.controllers;

import com.arneriso.mybusinessapp.domain.BusinessUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@ExtendWith(MockitoExtension.class)
class BusinessUserControllerTest {

    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private BusinessUserController businessUserController;

    private MockMvc mockMvc;

    private final Gson gson = new GsonBuilder().create();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(businessUserController)
                .setControllerAdvice(new ExceptionHandlerAdvice())
                .build();
    }

    @Test
    public void whenUserIsGenerated_thenIdIsPopulatedWithUUID() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/generate-business-user")).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        BusinessUser businessUser = gson.fromJson(result.getResponse().getContentAsString(), BusinessUser.class);
        assertThat(businessUser.getId()).isNotEmpty();
    }

    @Test
    public void whenUserIsValid_thenSaveAndReturnOK() throws Exception {
        MvcResult result = putUser(createValidUser());
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        verify(mongoOperations, times(1)).save(any());
    }

    @Test
    public void whenUserIdNull_thenReturnBadRequest() throws Exception {
        BusinessUser user = createValidUser();
        user.setId(null);
        MvcResult result = putUser(user);
        assertThat(result.getResponse().getStatus()).isEqualTo(400);
        verifyNoInteractions(mongoOperations);
    }

    @Test
    public void whenUserIdIsBlank_thenReturnBadRequest() throws Exception {
        BusinessUser user = createValidUser();
        user.setId(" ");
        MvcResult result = putUser(user);
        assertThat(result.getResponse().getStatus()).isEqualTo(400);
        verifyNoInteractions(mongoOperations);
    }

    @Test
    public void whenAgreementNotAccepted_thenReturnBadRequest() throws Exception {
        BusinessUser user = createValidUser();
        user.setAgreementToTerms(false);
        MvcResult result = putUser(user);
        assertThat(result.getResponse().getStatus()).isEqualTo(400);
        verifyNoInteractions(mongoOperations);
    }

    @Test
    public void whenNameIsNull_thenReturnBadRequest() throws Exception {
        BusinessUser user = createValidUser();
        user.setUserName(null);
        MvcResult result = putUser(user);
        assertThat(result.getResponse().getStatus()).isEqualTo(400);
        verifyNoInteractions(mongoOperations);
    }

    @Test
    public void whenNameIsBlank_thenReturnBadRequest() throws Exception {
        BusinessUser user = createValidUser();
        user.setUserName(" ");
        MvcResult result = putUser(user);
        assertThat(result.getResponse().getStatus()).isEqualTo(400);
        verifyNoInteractions(mongoOperations);
    }

    @Test
    public void whenSectorsNull_thenReturnBadRequest() throws Exception {
        BusinessUser user = createValidUser();
        user.setSectorIds(null);
        MvcResult result = putUser(user);
        assertThat(result.getResponse().getStatus()).isEqualTo(400);
        verifyNoInteractions(mongoOperations);
    }

    @Test
    public void whenNoSectorsSelected_thenReturnBadRequest() throws Exception {
        BusinessUser user = createValidUser();
        user.getSectorIds().clear();
        MvcResult result = putUser(user);
        assertThat(result.getResponse().getStatus()).isEqualTo(400);
        verifyNoInteractions(mongoOperations);
    }

    private BusinessUser createValidUser() {
        BusinessUser businessUser = new BusinessUser();
        businessUser.setId("id");
        businessUser.setUserName("username");
        businessUser.setAgreementToTerms(true);
        businessUser.setSectorIds(new ArrayList<>());
        businessUser.getSectorIds().add("1");
        return businessUser;
    }

    private MvcResult putUser(BusinessUser user) throws Exception {
        return mockMvc.perform(put("/api/business-user")
                        .content(gson.toJson(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }
}
