﻿function purchase_data()
{
  //Inicializacion de la variable page, para buscar los elementos del URL obtenido
  var page = Sys.Browser("*").Page("*");
  page.Wait();
  
  var firts_name = KeywordTests.Test1.Variables.datos1.Value("FIRSTNAME")
  var last_name = KeywordTests.Test1.Variables.datos1.Value("LASTNAME")
  var zip_code = KeywordTests.Test1.Variables.datos1.Value("ZIP")
  var min = 0

  //cart image 
  
  do{
    var image_cart = page.WaitElement("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']")
    min++
  }while(image_cart==null && min>=5)
  
  if(image_cart.Exists){
    image_cart.Click()
    Log.Checkpoint("clicked on the image cart successfully")
  }else{
    Log.Error("i dont clicked image of the cart")
  }
  
  // clicked checkout
  
  do{
    var button_checkout = page.WaitElement("//button[@id='checkout']")
    min++
  }while(button_checkout==null && min>=5)
  
  if(button_checkout.Exists){
    button_checkout.Click()
    Log.Checkpoint("you successfully clicked on checkout")
  }else{
    Log.Error("you did not successfully click on checkout")
  }
  
  // firts name
  
  do{
    var campo_name = page.WaitElement("//input[@id='first-name']")
    min++
  }while(campo_name==null && min>=5)
  
  if(campo_name.Exists){
    campo_name.SetText(firts_name)
  }
  
  //last name 
  
  do{
    var campo_lastname = page.WaitElement("//input[@id='last-name']")
    min++
  }while(campo_lastname==null && min>=5)
  
  if(campo_lastname.Exists){
    campo_lastname.SetText(last_name)
    Log.Checkpoint("i entered the middle name correctly")
  }else{
    Log.Error("i did not enter the middle name correctly")
    }
    
  //zip code 
  
  do{
    var field_zipcode = page.WaitElement("//input[@id='postal-code']")
    min++
  }while(field_zipcode==null && min>=5)
  
  if(field_zipcode.Exists){
    field_zipcode.SetText(zip_code)
    Log.Checkpoint("i entered the zip code correctly")
  }else{
    Log.Error("i did not eneter the zip code correctly")
  }
  
  // button continue 
  do{
    var button_continue = page.WaitElement("//input[@id='continue']", 5000)
    min++
  }while(button_continue==null && min>=5)
  
  if(button_continue.Exists){
    button_continue.Click()
    Log.Checkpoint("clicked on the continue button")
  }else{
    Log.Error("Did not click on the continue button")
  }
  
  // button finished 
  
  do{
    var btton_finished = page.WaitElement("//button[@id='finish']", 5000)
    min++
  }while(btton_finished==null && min>=5)
  
  if(btton_finished.Exists){
    
    btton_finished.Click()
    Log.Checkpoint("clicked on the finishied button")
  }else{
    Log.Error("did not click on the finished button")
  }
  
  //back Home 
  
  do{
    var field_backhome = page.WaitElement("//button[@id='back-to-products']")
    min++
  }while(field_backhome==null && min>=5)
  
  if(field_backhome.Exists){
    field_backhome.Click()
    Log.Checkpoint("clicked on the backhome button")
  }else{
    Log.Error("did not click on the backhome button")
  }
}