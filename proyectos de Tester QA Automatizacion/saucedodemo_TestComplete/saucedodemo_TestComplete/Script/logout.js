function logout()
{
   //Inicializacion de la variable page, para buscar los elementos del URL obtenido
  var page = Sys.Browser("chrome").Page("*");
  page.Wait();
  
  //contador 
   var min = 0 
  //options
  
  do{
    var field_option = page.WaitElement("//div[button[@id='react-burger-menu-btn']]")
    min++
  }while(field_option==null && min>=5)
  
  if(field_option.Exists){
    field_option.Click()
    Log.Checkpoint("clicked field_options button")
  }else{
    Log.Error("i did on the clicked field_option button")
  }
  
  //Reset
  
  do{
    var reset_app = page.WaitElement("//a[@id='reset_sidebar_link']")
    min++
  }while(reset_app==null && min>=5)
  
  if(reset_app.Exists){
    reset_app.Click()
    Log.Checkpoint("clicked in button reset")
  }else{
    Log.Error("not did clicked in button reset")
  }
  
  //logout
  do{
    var button_logout = page.WaitElement("//a[@id='logout_sidebar_link']")
    min++
  }while(button_logout==null && min>=5)
  
  if(button_logout.Exists){
    button_logout.Click()
    Log.Checkpoint("Clicked correctly on the logout button")
    
  }else{
    Log.Error("i did not clicked logout")
  }
}