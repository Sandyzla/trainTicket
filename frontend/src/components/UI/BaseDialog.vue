<template>
  <div class="wrapper">
    <div class="header-content">
      <div class="header">
        <slot name="header"></slot>
      </div>
        <div class="content">
          <slot name="content">
            
          </slot>
        </div>
        <div class="button-content">
          <slot name="button"></slot>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      c_style: "simple"
    }
  }
}
</script>


<style scoped>
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: rgba(205, 205, 205, 0.534);
  left: 0px;
  top: 0px;
  width: 100%;
  height: 100%;
  position: fixed;
  z-index: 999;
}

.header-content{
  width: 350px;
  z-index: 999;
  background-color: rgb(255, 255, 255);
}

.content {
  margin: 20px;
  z-index: 999;
  
}

.button-content {
  display: flex;
  justify-content: center;
  align-items: center;
  padding-top: 5px;
  padding-bottom: 5px;
  border-top: 1px solid rgb(113, 113, 113);
}


</style>