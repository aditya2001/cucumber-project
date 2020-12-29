package Utils;

public class Orep {

    public static class CredentialClass {
        public static final String userId = Constants.NAME + "~" + "email";
        public static final String password = Constants.XPATH + "~" + "//*[@name='password']";
        public static final String login = Constants.XPATH + "~" + "//span[contains(text(),'Login')]";
    }
        public static class HomePage {
        public static final String quickbook = Constants.XPATH + "~" +"//div[contains(text(),'Quick Book')]";
        public static final String dashboard= Constants.XPATH + "~" +" //strong[contains(text(),'Dashboard')]";
    }
    public static class General {
        public static final String gen = Constants.XPATH + "~" + "//a[contains(text(),'General')]";
        public static final String currency = Constants.XPATH + "~" + "//a[contains(text(),'Currencies')]";
        public static final String add = Constants.XPATH + "~" + "//a[@class='btn btn-success xcrud-action']";
        public static final String Name = Constants.XPATH + "~" + "//body[contains(@class,'pace-done')]/div[@class='wrapper']/div[@id='content']/div[@class='panel panel-default']/div[@class='panel-body']/div[@class='xcrud']/div[@class='xcrud-container']/div[@class='xcrud-ajax']/div[@class='xcrud-view']/div/div[1]/div[1]/input[1]";
        public static final String Symbol = Constants.XPATH + "~" + "//body[contains(@class,'pace-done')]/div[contains(@class,'wrapper')]/div[@id='content']/div[contains(@class,'panel panel-default')]/div[contains(@class,'panel-body')]/div[contains(@class,'xcrud')]/div[contains(@class,'xcrud-container')]/div[contains(@class,'xcrud-ajax')]/div[contains(@class,'xcrud-view')]/div/div[2]/div[1]/input[1]";
        public static final String Code = Constants.XPATH + "~" + "//body[contains(@class,'pace-done')]/div[@class='wrapper']/div[@id='content']/div[@class='panel panel-default']/div[@class='panel-body']/div[@class='xcrud']/div[@class='xcrud-container']/div[@class='xcrud-ajax']/div[@class='xcrud-view']/div/div[3]/div[1]/input[1]";
        public static final String Rate = Constants.XPATH + "~" + "//body[contains(@class,'pace-done')]/div[contains(@class,'wrapper')]/div[@id='content']/div[contains(@class,'panel panel-default')]/div[contains(@class,'panel-body')]/div[contains(@class,'xcrud')]/div[contains(@class,'xcrud-container')]/div[contains(@class,'xcrud-ajax')]/div[contains(@class,'xcrud-view')]/div/div[4]/div[1]/input[1]";
        public static final String Active = Constants.XPATH + "~" + "//select[contains(@class,'xcrud-input form-control')]";
        public static final String Submit = Constants.XPATH + "~" + "//a[contains(@class,'btn btn-primary xcrud-action')]";
        public static final String Search = Constants.XPATH + "~" + "//a[@class='xcrud-search-toggle btn btn-default']";
        public static final String Searchbox = Constants.XPATH + "~" + "//span[@class='xcrud-search form-inline']//input[@type='text']";
        public static final String Go = Constants.XPATH + "~" + "//a[@class='xcrud-action btn btn-primary']";
        public static final String CheckBox = Constants.XPATH + "~" + "//tr[contains(@class,'xcrud-row xcrud-row-0')]//td//input[contains(@type,'checkbox')]";
        public static final String DeleteSelected = Constants.XPATH + "~" + "//strong[contains(text(),'Delete Selected')]";
        public static final String View = Constants.XPATH + "~" + "//i[@class='fa fa-search']";
        public static final String Return = Constants.XPATH + "~" + "//a[@class='btn btn-warning xcrud-action']";
    }
     public static class View {
       public static final String Name = Constants.XPATH + "~" + "//body[contains(@class,'pace-done')]/div[contains(@class,'wrapper')]/div[@id='content']/div[contains(@class,'panel panel-default')]/div[contains(@class,'panel-body')]/div[contains(@class,'xcrud')]/div[contains(@class,'xcrud-container')]/div[contains(@class,'xcrud-ajax')]/div[contains(@class,'xcrud-view')]/table[contains(@class,'table')]/tbody/tr[1]/td[2]";
       public static final String Symbol = Constants.XPATH + "~" + "//body[contains(@class,'pace-done')]/div[contains(@class,'wrapper')]/div[@id='content']/div[contains(@class,'panel panel-default')]/div[contains(@class,'panel-body')]/div[contains(@class,'xcrud')]/div[contains(@class,'xcrud-container')]/div[contains(@class,'xcrud-ajax')]/div[contains(@class,'xcrud-view')]/table[contains(@class,'table')]/tbody/tr[2]/td[2]";
       public static final String Code = Constants.XPATH + "~" + "//body[contains(@class,'pace-done')]/div[contains(@class,'wrapper')]/div[@id='content']/div[contains(@class,'panel panel-default')]/div[contains(@class,'panel-body')]/div[contains(@class,'xcrud')]/div[contains(@class,'xcrud-container')]/div[contains(@class,'xcrud-ajax')]/div[contains(@class,'xcrud-view')]/table[contains(@class,'table')]/tbody/tr[3]/td[2]";
       public static final String Rate = Constants.XPATH + "~" + "/html[1]/body[1]/div[2]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[4]/td[2]";
       public static final String Active = Constants.XPATH + "~" + "//td[contains(text(),'Yes')]";

     }

    }


