﻿function login()
{
  //Inicializacion de la variable page, para buscar los elementos del URL obtenido
  var page = Sys.Browser("*").Page("*");
  page.Wait();
  
  var user = KeywordTests.Test1.Variables.datos1.Value("USER")  
  var pass = KeywordTests.Test1.Variables.datos1.Value("PASS")
  
  //contador 
  var min = 0
  
  //ingresar usuario 
  do{
    var username = page.WaitElement("//input[@id='user-name']")
    min++
  }while(username==null && min>=5)
  
  if(username.Exists){
    username.SetText(user)
    Log.Checkpoint("the user was entered correctly")
  }else{
    Log.Error("the user was not entered correctly")
  }
  
  //ingresar contrasena
  
  do{
    var password = page.WaitElement("//input[@id='password']")
    min++
  }while(password==null && min>=5)
  
  if(password.Exists){
    password.SetText(pass)
    Log.Checkpoint("password was entered correctly")
  }else{
    Log.Error("password was not entered correctly")
  }
  
  // Hacer click en el boton login 
  do{
    var bton_login = page.WaitElement("//input[@id='login-button']")
    min++
  }while(bton_login==null && min>=5)
  
  if(bton_login.Exists){
    bton_login.Click()
    Log.Checkpoint("correctly clicked on rhe login button")
  }else{
    Log.Error("you did not click the login button correctly")
  }
  aqUtils.Delay(1000)
    Log.Message("Alert text: " + page.Alert.Message);
   page.Alert.Button("OK").Click();
     
  }