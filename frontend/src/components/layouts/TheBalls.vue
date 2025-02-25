<template>
  <canvas style="position:fixed;z-index:-1;" ref="canvasRef"></canvas>
  <slot>

  </slot>
</template>

<script>

export default {
  mounted() {
    this.$refs.canvasRef.width = window.innerWidth;
    this.$refs.canvasRef.height = window.innerWidth;

    for (let i = 0; i < 5; i++) {
      let j = this.random(80, 100);
      this.balls.push({
        x: this.random(0 + j, this.width - j),
        y: this.random(0 + j, this.height - j),
        velX: this.random(-10, 10) / 40,
        velY: this.random(-10, 10) / 40,
        color: this.randomColor(),
        size: j,
      });
    }
    this.draw();
    this.loop();
  },
  data() {
    return {
      balls: [],
      ctx: this.$refs.canvasRef,
      width: window.innerWidth,
      height: window.innerHeight
    }
  },
  methods: {
    random(min, max) {
      return Math.floor(Math.random() * (max - min)) + min;
    },
    randomColor() {
      return 'rgb(' +
        this.random(200, 240) + ', ' +
        this.random(200, 240) + ', ' +
        this.random(200, 240) + ')';
    },
    draw() {
      for (let i = 0; i < this.balls.length; i++) {
        this.$refs.canvasRef.getContext('2d').beginPath();
        this.$refs.canvasRef.getContext('2d').fillStyle = this.balls[i].color;
        this.$refs.canvasRef.getContext('2d').arc(this.balls[i].x, this.balls[i].y, this.balls[i].size, 0, 2 * Math.PI);
        this.$refs.canvasRef.getContext('2d').fill();

        if ((this.balls[i].x + this.balls[i].size) >= this.width) {
          this.balls[i].velX = -(this.balls[i].velX);
        }

        if ((this.balls[i].x - this.balls[i].size) <= 0) {
          this.balls[i].velX = -(this.balls[i].velX);
        }

        if ((this.balls[i].y + this.balls[i].size) >= this.height) {
          this.balls[i].velY = -(this.balls[i].velY);
        }

        if ((this.balls[i].y - this.balls[i].size) <= 0) {
          this.balls[i].velY = -(this.balls[i].velY);
        }

        this.balls[i].x += this.balls[i].velX;
        this.balls[i].y += this.balls[i].velY;
      }
    },
    loop() {
      //先覆盖上一次画布,但又留下半透明的轨迹
      this.$refs.canvasRef.getContext('2d').fillStyle = 'rgba(220, 255, 245, 0.5)';
      this.$refs.canvasRef.getContext('2d').fillRect(0, 0, this.width, this.height);
      this.draw();
      // for (let i = 0; i < this.balls.length; i++) {
      //   this.balls[i].draw();
      //   this.balls[i].update();
      // }
      requestAnimationFrame(this.loop);
    }

  }

}

</script>

<style scoped>
canvas {
  display: block;
}

/* body{
  margin: 0;
} */
</style>