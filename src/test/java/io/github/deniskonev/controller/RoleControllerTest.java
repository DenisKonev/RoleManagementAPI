package io.github.deniskonev.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

//TODO Починить тесты
@WebMvcTest(RoleController.class)
@AutoConfigureMockMvc(addFilters = false)
class RoleControllerTest {
/*
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    private RoleRequestDTO invalidDTO;
    private RoleResponseDTO responseDTO;
    private RoleResponseDTO updatedResponseDTO;

    @BeforeEach
    void setUp() {
        responseDTO = createUserResponseDTO();
        invalidDTO = new RoleRequestDTO();
        updatedResponseDTO = createUpdatedUserResponseDTO();
    }

    @Test
    @DisplayName("Get all users")
    void testGetAllUsers() throws Exception {
        Mockito.when(roleService.getAllUsers()).thenReturn(Collections.singletonList(createUserResponseDTO()));

        mockMvc.perform(get(BASE_API + USERS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(USER_ID))
                .andExpect(jsonPath("$[0].username").value(USERNAME))
                .andExpect(jsonPath("$[0].email").value(EMAIL));
    }

    @Test
    @DisplayName("Get user by id: Success")
    void testGetUserById_Found() throws Exception {
        Mockito.when(roleService.getUserById(USER_ID)).thenReturn(Optional.of(createUserResponseDTO()));

        mockMvc.perform(get(BASE_API + USERS + "/" + USER_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(USER_ID))
                .andExpect(jsonPath("$.username").value(USERNAME))
                .andExpect(jsonPath("$.email").value(EMAIL));
    }

    @Test
    @DisplayName("Get user by id: Not found")
    void testGetUserById_NotFound() throws Exception {
        Mockito.when(roleService.getUserById(USER_ID)).thenReturn(Optional.empty());

        mockMvc.perform(get(BASE_API + USERS + "/" + USER_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Create user: Success")
    void testCreateUser_Success() throws Exception {
        Mockito.when(roleService.createUser(any(RoleRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post(BASE_API + USERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createUserRequestDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(USER_ID))
                .andExpect(jsonPath("$.username").value(USERNAME))
                .andExpect(jsonPath("$.email").value(EMAIL));
    }

    @Test
    @DisplayName("Create user: Validation error")
    void testCreateUser_ValidationError() throws Exception {
        mockMvc.perform(post(BASE_API + USERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(invalidDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Update user: Success")
    void testUpdateUser_Success() throws Exception {
        Mockito.when(roleService.updateUser(any(Long.class), any(RoleRequestDTO.class))).thenReturn(Optional.of(updatedResponseDTO));

        mockMvc.perform(put(BASE_API + USERS + "/" + USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createUserRequestDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(USER_ID))
                .andExpect(jsonPath("$.username").value(USERNAME))
                .andExpect(jsonPath("$.email").value(NEW_EMAIL));
    }

    @Test
    @DisplayName("Update user: Not found")
    void testUpdateUser_NotFound() throws Exception {
        Mockito.when(roleService.updateUser(any(Long.class), any(RoleRequestDTO.class))).thenReturn(Optional.empty());

        mockMvc.perform(put(BASE_API + USERS + "/" + USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createUserRequestDTO())))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Delete user: Success")
    void testDeleteUser_Success() throws Exception {
        Mockito.when(roleService.deleteUser(USER_ID)).thenReturn(true);

        mockMvc.perform(delete(BASE_API + USERS + "/" + USER_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Delete user: Not found")
    void testDeleteUser_NotFound() throws Exception {
        Mockito.when(roleService.deleteUser(USER_ID)).thenReturn(false);

        mockMvc.perform(delete(BASE_API + USERS + "/" + USER_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }*/
}
