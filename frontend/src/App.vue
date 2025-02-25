
<template>
    <div id="app">
      <router-view></router-view>
    </div>
</template>

<style>

body{
  font-family: 'Courier New', Courier, monospace;
}

input {
  font-size: 15px;
  border: none;
  border-bottom: 2px solid rgb(90, 90, 90);
  background-color: white;
  height: 30px;
  vertical-align: middle;
  margin-bottom: 5px;
  margin-top: 5px;
  font-family: 'Courier New', Courier, monospace;
}

input:focus {
  background-color: white;
  border-bottom: 2px solid rgb(90, 90, 90);
  outline: none;
}

label{
  font-size: 15px;
  vertical-align: middle;
  /* display: flex; */
    align-items: center; /* 垂直居中 */
    justify-content: center; 
    
}



.fineInput {
  background-color: rgb(169, 239, 207);
}

body {
  margin: 0;

}

.header-content{
  width: 700px;
  border: 1px solid rgb(91, 91, 91);
  margin-bottom: 10px;
  border-radius: 5px 5px 0px 0px;
}

.header{
  padding: 5px;
  background-color: rgb(91, 91, 91);
  color: white;
}

.content{
  background-color: white;
  color: rgb(91, 91, 91);
  padding: 5px;
}
</style>
