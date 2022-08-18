

    function compareAndStyle(){
        const p1hp = Number(document.getElementById("p1HP").innerText);
        const p1a = Number(document.getElementById("p1A").innerText);
        const p1d = Number(document.getElementById("p1D").innerText);
        const p1sa = Number(document.getElementById("p1SA").innerText);
        const p1sd = Number(document.getElementById("p1SD").innerText);
        const p1s = Number(document.getElementById("p1S").innerText);

        const p2hp = Number(document.getElementById("p2HP").innerText);
        const p2a = Number(document.getElementById("p2A").innerText);
        const p2d = Number(document.getElementById("p2D").innerText);
        const p2sa = Number(document.getElementById("p2SA").innerText);
        const p2sd = Number(document.getElementById("p2SD").innerText);
        const p2s = Number(document.getElementById("p2S").innerText);

         compare(p1hp,p2hp,"HP");
         compare(p1a,p2a,"A");
         compare(p1d,p2d,"D");
         compare(p1sa,p2sa,"SA");
         compare(p1sd,p2sd,"SD");
         compare(p1s,p2s,"S");

         cardCompare();
    }

    function compare(p1,p2,status){
      var p1stat = "p1"+status;
      var p2stat = "p2"+status;
      if(p1 == p2){
         document.getElementById(p1stat).style.color = "#002080";
         document.getElementById(p2stat).style.color = "#002080";
      }else if(p1 > p2){
         document.getElementById(p1stat).style.color = "#006600";
         document.getElementById(p2stat).style.color = "#800000";
      }else if(p2 > p1){
         document.getElementById(p2stat).style.color = "#006600";
         document.getElementById(p1stat).style.color = "#800000";
      }
    }

    function cardCompare(){
      var scorep1 = Number(document.getElementById("p1").innerText);
      var scorep2 = Number(document.getElementById("p2").innerText);


      if(scorep1 == scorep2){
         document.getElementById("card1").style.backgroundColor = "#FFFFFF";
         document.getElementById("card2").style.backgroundColor = "#FFFFFF";
      }else if(scorep1 > scorep2){
         document.getElementById("card1").style.backgroundColor = "#e6ffe6";
         document.getElementById("card2").style.backgroundColor = "#ffe6e6";
      }else{
         document.getElementById("card2").style.backgroundColor = "#e6ffe6";
         document.getElementById("card1").style.backgroundColor = "#ffe6e6";
      }
   
      
   }