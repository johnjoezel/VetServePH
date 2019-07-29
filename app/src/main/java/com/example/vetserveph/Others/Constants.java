package com.example.vetserveph.Others;

public class Constants {
    private static final String LOCALHOST="192.168.1.10";
    private static final String ROOT_URL="http://"+LOCALHOST+"/vetserveph/controller/";
    public static final String URL_REGISTER_USER=ROOT_URL+"petowner_registration.php/";
    public static final String URL_GET_IMAGE="http://"+LOCALHOST+"/vetserveph/uploads/";
    public static final String URL_LOGIN=ROOT_URL+"petowner_login.php/";
    public static final String URL_GET_SPINNERITEMS = ROOT_URL + "getSpinneritems.php";
    public static final String URL_ADD_PET =  ROOT_URL + "addpet.php";
    public static final String URL_LIST_OF_PETS =  ROOT_URL + "getlistofpets.php";
    public static final String URL_LOGOUT=ROOT_URL+"logout.php/";


}
