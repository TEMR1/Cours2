
window.addEventListener("load", (event) => {
  var button = document.getElementById("button");
  var span = document.getElementById("span");
  var counter = 0;

  button.addEventListener("click", function (){
      counter++;
      span.innerHTML = "Button Clicked " + counter;
  });
});