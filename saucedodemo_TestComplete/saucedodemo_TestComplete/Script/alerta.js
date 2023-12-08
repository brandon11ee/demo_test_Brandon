function HandleAlert() {
  
  var page = Sys.Browser("*").Page("*");
  page.Wait();

  var alert = Sys.WaitWindow(0, 5000);
  
  if (alert) {
  
    Log.Message(alert.Caption); 
    
    alert.Close();
    
  }

}