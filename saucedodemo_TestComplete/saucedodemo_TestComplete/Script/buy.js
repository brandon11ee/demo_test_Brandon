﻿function buy()
{
  //Inicializacion de la variable page, para buscar los elementos del URL obtenido
  var page = Sys.Browser("*").Page("*");
  page.Wait();
  
  
  var min = 0
  
   //make a purchase
  
  do{
    var make_labs_backpack = page.WaitElement("//a[contains(.,'Sauce Labs Backpack')]")
    min++
  }while(make_labs_backpack==null && min>=5)
  
  if(make_labs_backpack.Exists){
    make_labs_backpack.Click()
    Log.Checkpoint("make a purchase correctly")
  }else{
    Log.Error("make a not purchase")
  }
  
  // add to cart button
  
  do{
    var add_cart = page.WaitElement("//button[contains (.,'Add to cart')]")
    min++
  }while(add_cart==null && min>=5)
  
  if(add_cart.Exists){
    add_cart.Click()
    Log.Checkpoint("add to cart correctly")
  }else{
    Log.Error("I dont add cart correctly")
  }
  
  //back to products
  
  do{
    var back_products = page.WaitElement("//button[@id='back-to-products']")
    min++
  }while(back_products==null && min>=5)
  
  if(back_products.Exists){
    back_products.Click()
    Log.Checkpoint("back to products correctly")
  }else{
    Log.Error("i dont to back products")
  }
  
  
}