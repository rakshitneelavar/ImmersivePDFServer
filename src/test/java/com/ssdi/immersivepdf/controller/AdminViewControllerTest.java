package com.ssdi.immersivepdf.controller;

import com.ssdi.immersivepdf.dao.BookEntryDao;
import com.ssdi.immersivepdf.dao.GetBooksDao;
import com.ssdi.immersivepdf.dao.UserDataDao;
import com.ssdi.immersivepdf.model.Register.User;
import com.ssdi.immersivepdf.model.View.Book;
import com.ssdi.immersivepdf.model.View.Books;
import com.ssdi.immersivepdf.model.admin.Users;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.*;
import java.util.ArrayList;

import static org.apache.http.client.methods.RequestBuilder.post;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AdminViewControllerTest {

    @Mock
    private UserDataDao userDataDao;
    @Mock
    private GetBooksDao getBooksDao;
    private BookEntryDao bookEntryDao;
    private MockMvc mockMvc;
    private Books mockBooks;
    private Book bookMock;

    @InjectMocks
    private AdminViewController adminViewController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(adminViewController).build();
    }

    @After
    public void tearDown() throws Exception {
    }


    // Test methods for fetching all user details.


    @Test
    public void getAllUserMvcMappingTest(){

        //request is not necessary
        JSONObject request = new JSONObject();
        try {
            request.put("bookid",1);
            request.put("isfavorite",true);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try{
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/getUsers")
                    .contentType(MediaType.APPLICATION_JSON).content(request.toString()))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void getAllUsersUsingMock() {
        Users users = new Users();
        User user = new User("Robert","Downey","robert@gmail.com","password",true);
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        users.setUsers(userList);
        try{
            when(userDataDao.getAllUsers()).thenReturn(users);
            assertTrue(userDataDao.getAllUsers().getUsers().size() == 1);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    // Test methods for fetching all books.
    @Test
    public void getAllBooksMvcMappingTest(){
        //request is not necessary
        JSONObject request = new JSONObject();
        try {
            request.put("userid",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try{
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/getAllBooks")
                    .contentType(MediaType.APPLICATION_JSON).content(request.toString()))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getAllBooksUsingMock() {
        Users users = new Users();
        User user = new User("Robert","Downey","robert@gmail.com","password",true);

        Book book = new Book();
        ArrayList<Book> booksarray = new ArrayList<>();
        booksarray.add(book);
        Books booksCollection = new Books();
        booksCollection.setBookCollection(booksarray);

        try{
            when(getBooksDao.getAllBooksForUser(user)).thenReturn(booksCollection);
            Books resultantBooks = getBooksDao.getAllBooksForUser(user);
            assertTrue(resultantBooks.getBookCollection().size() == 1);
        }catch (Exception e){
            assertFalse(true);
            System.out.println(e.getLocalizedMessage());
        }
    }


    @Test
    public void deleteUserMvcMappingTest(){

        //request is not necessary
        JSONObject request = new JSONObject();
        try {
            request.put("userid",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try{
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/deleteUser")
                    .contentType(MediaType.APPLICATION_JSON).content(request.toString()))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUserUsingMock() {
        Users users = new Users();
        User user = new User("Robert","Downey","robert@gmail.com","password",true);
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        users.setUsers(userList);
        try{
            when(userDataDao.deleteUser(user)).thenReturn(true).thenReturn(false);
            assertTrue(userDataDao.deleteUser(user) == true);
            assertTrue(userDataDao.deleteUser(user) == false);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }


    @Test
    public void grantPrivilegeMvcMappingTest(){
        //request is not necessary
        JSONObject request = new JSONObject();
        try {
            request.put("userid",1);
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/grantPrivilege")
                    .contentType(MediaType.APPLICATION_JSON).content(request.toString()))
                    .andExpect(status().isOk());
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void grantPrivilegeUsingMock() {

        User user = new User("Robert","Downey","robert@gmail.com","password",true);

        try{
            when(userDataDao.allowPasswordReset(user)).thenReturn(true).thenReturn(false);
            assertTrue(userDataDao.allowPasswordReset(user) == true);
            assertTrue(userDataDao.allowPasswordReset(user) ==  false);
        }catch (Exception e){
            assertFalse(true);
            System.out.println(e.getLocalizedMessage());
        }
    }


    @Test
    public void deleteBookMvcMappingTest(){
        //request is not necessary
        JSONObject request = new JSONObject();
        try {
            request.put("userid",1);
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/deleteBook")
                    .contentType(MediaType.APPLICATION_JSON).content(request.toString()))
                    .andExpect(status().isOk());
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void deleteBookUsingMock() {
        Book book = new Book();
        try{
            when(bookEntryDao.deleteBook (book)).thenReturn(200).thenReturn(400).thenReturn(401);
            assertTrue(bookEntryDao.deleteBook(book) == 200);
            assertTrue(bookEntryDao.deleteBook(book) == 400);
            assertTrue(bookEntryDao.deleteBook(book) == 401);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

}