<template>
  <button :type="type" class="hvr-radial-out">
    <slot></slot>
  </button>
</template>

<script>
export default {
  props: ['type', 'mode']
}
</script>

<style scoped>
button {
  color:white;
  padding: 10px;
  border: 1.5px #000000;
  border-radius: 5px;
  cursor: pointer;
  background-color: rgb(72, 189, 189);
  /* border-radius: 10px; */
  margin-inline: 10px;
  font-family: Georgia, serif;

}

/* Radial Out */
.hvr-radial-out {
  display: inline-block;
  vertical-align: middle;
  /* background-color: rgb(91, 211, 211); */
  -webkit-transform: perspective(1px) translateZ(0);
  transform: perspective(1px) translateZ(0);
  box-shadow: 0 0 1px rgba(0, 0, 0, 0);
  position: relative;
  overflow: hidden;
  /* -webkit-transition-property: color;
  transition-property: color; */
  -webkit-transition-duration: 0.2s;
  transition-duration: 0.3s;
}

.hvr-radial-out:before {
  content: "";
  position: absolute;
  z-index: -1;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #f459b4;
  border-radius: 100%;
  -webkit-transform: scale(0);
  transform: scale(0);
  /* -webkit-transition-property: transform;
  transition-property: transform; */
  -webkit-transition-duration: 0.3s;
  transition-duration: 0.3s;
  -webkit-transition-timing-function: ease-out;
  transition-timing-function: ease-out;
}

/* .hvr-radial-out:focus, */

.hvr-radial-out:hover,
.hvr-radial-out:active {
  color: rgb(255, 255, 255);
}

/* .hvr-radial-out:focus:before, */
.hvr-radial-out:hover:before,

.hvr-radial-out:active:before {
  -webkit-transform: scale(2);
  transform: scale(2);
  /* background-color: rgb(91, 211, 211); */
}
</style>