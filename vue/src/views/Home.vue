<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <map-api />
  </div>
</template>


<script>
import MapApi from '@/components/MapApi.vue'


export default {
  name: "home",
  components : {
    MapApi
  }

};
</script>
