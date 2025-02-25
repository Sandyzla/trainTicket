<template>
  <div>
    <slot></slot>
  </div>
</template>

<script>
export default {
  props: ['mode', 'width']
}
</script>

<style scoped>
div {
  /* border-radius: 12px; */
  background-color: #e4f4fbf2;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
  padding: 0rem;
  text-align: center;
  border-radius: 8px;
  /* max-width: 40%; */
}

div.simple {
  border: 4px dashed #b7e1f4f2;
  /* box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26); */
  background-color: rgb(255, 255, 255);
  padding: 0rem;
  text-align: center;
  border-radius: 8px;
  box-shadow: none;
  font-size: 16px;
}
</style>