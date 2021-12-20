// window.onload = function() {
                    

                  //  document.getElementById('cantidad-servicios').addEventListener('click' , function());
                    
                //    function(){
              //          alert('hola');
                        //var cantidad = document.getElementById('cantidad-servicios').value;
                        //for (var i = 1; i < cantidad; i++) {
                        //    document.getElementById("div-servicio-" + i).className = "col-lg-6 col-md-6 form-group";
                        //}
            //        }
                    
                
          // }



//ARREGLAR
//funciona agregarndo el codigo en el html directo al final del body
document.getElementById("cantidad-servicios").addEventListener("change", myFunction);

      function myFunction() {
        var cantidad = document.getElementById('cantidad-servicios').value;
        var cantServicioMaxima = 5;
        alert(cantidad);
        for (var i = 1; i <= cantidad; i++) {
          document.getElementById("div-servicio-" + i).className = "col-lg-6 col-md-6 form-group";
        }
        alert("fin");
        for (var j = cantServicioMaxima; j < cantidad ; j--) {  
          document.getElementById("div-servicio-" + j).className += "borrar";
        }
        alert("fin2");
      }