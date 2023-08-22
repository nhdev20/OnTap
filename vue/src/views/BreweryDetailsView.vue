<template>
<div class="home">
    <!-- <app-header/> -->
    
    
    <!-- <div id="details" class="grid-container">
      <h3 id="details-header">Details</h3>
      <div id="details-content">details content</div> -->
      <brewery-details></brewery-details>
      <!-- </div> -->

    <!-- <div id="map" class="grid-container">
      <h2 id="map-header">Map</h2>
      <div id="map-content">map content</div>
      </div> -->
    
  </div>
</template>

<script>
import BreweryDetails from '../components/BreweryDetails'

export default {
    name: 'brewery-details-view',
    components: {
        BreweryDetails
    }
}
</script>

<style scoped>
/* .home {
  display: grid;
    grid-template-columns: 2fr 2fr;
    grid-template-areas: 
    "details-header map-header"
    "map-content map-content";
    row-gap: 20px;
    
}
#details-header {
  grid-area: details-header;
}
#map-header {
  grid-area: map-header;
}
#details-content {
  grid-area: details-content;
}
#map-content {
  grid-area: map-content;
}
.grid-container {
  border: 1px solid black;
  padding: 20px;
} */



</style>