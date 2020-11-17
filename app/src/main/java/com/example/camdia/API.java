package com.example.camdia;

public class API {

    private static final String ROOT_URL = "http://192.168.0.11/HeroApi/includes/Api.php?apicall=";

    public static final String URL_veri = ROOT_URL + "verifica";
    public static final String URL_READ = ROOT_URL + "get";
    public static final String URL_UPDATE = ROOT_URL + "update";
    public static final String URL_hist = ROOT_URL + "gethist";
    public static final int CODE_GET_REQUEST = 1024;
    public static final int CODE_POST_REQUEST = 1025;



}
