﻿function logout()
{
   //Inicializacion de la variable page, para buscar los elementos del URL obtenido
  var page = Sys.Browser("*").Page("*");
  page.Wait();
  
  //options
  
  do{
    var field_option = page.WaitElement("//button[@id='react-burger-menu-btn']")
    min++
  }while(field_option==null && min>=5)
  
  if(field_option.Exists){
    field_option.Click()
    Log.Checkpoint("clicked field_options button")
  }else{
    Log.Error("i did on the clicked field_option button")
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