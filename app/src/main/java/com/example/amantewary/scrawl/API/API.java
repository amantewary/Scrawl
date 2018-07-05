package com.example.amantewary.scrawl.API;

public class API {
    private static final String API_URL = "https://web.cs.dal.ca/~kamath/QA_Devint/NoteApi/v1/Api.php?apicall=";
    
    public static final String CREATE_URL = API_URL + "createnote";
    public static final String RETRIEVE_URL = API_URL + "getnotes";
    public static final String UPDATE_URL = API_URL + "updatenote";
    public static final String DELETE_URL = API_URL + "deletenote&id=";
}
