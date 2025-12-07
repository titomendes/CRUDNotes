package example.CRUDNOTES.entity.enums;


public enum Role  {

    ADMIN, USER;

    //o jwt usa ROLE_ amtes do enum por isso fica já criado e é so enviar este
    public String getAuthority(){return "ROLE_"+ name();}


}
