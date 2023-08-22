<template>
  <div class="home">
    <!-- <app-header/> -->

    <div id="full-container">
      <div id="header-section">
      </div>
      <div id="list-section">
      </div>
    </div>
    
    
    <!-- <div id="filter" class="grid-container"> -->
      <div id="filter-header" class="page-header">Filter</div>
      <div id="filter-content"><brewery-list-filters /></div>
      <!-- </div> -->

    <!-- <div class="grid-container"> -->
      <div id="brewerylist-header" class="page-header">Brewery List</div>
      <div id="brewerylist-content"><brewery-list /></div>
      <!-- </div> -->

    <!-- <div id="map" class="grid-container"> -->
      <!-- <div id="map-header" class="page-header">Map</div>
      <div id="map-content">MultipleBreweryMap 
        <multiple-brewery-map></multiple-brewery-map> 
      </div> -->
      <!-- </div> -->
    
  </div>
</template>

<script>

import BreweryList from '../components/BreweryList.vue';
import BreweryListFilters from '../components/BreweryListFilters.vue';
// import AppHeader from '../components/AppHeader.vue'
// import MultipleBreweryMap from '../components/MultipleBreweryMap.vue';

export default {
  name: "home",
  components: {
    // AppHeader,
    BreweryList,

    // MultipleBreweryMap,
    BreweryListFilters,

  }

    
    
};
</script>

<style scoped>

.header-section {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas: "filter-header brewerylist-header brewerylist-header";
}

.list-section {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas: "filter-content brewerylist-content brewerylist-content";
}

.home {
  background: rgba(251,230,194);
  border: 1px solid rgb(172, 13, 13);
  border-top: none;
  border-radius: 0 0 10px 10px;
  padding: 30px;
  padding-top: 0;
  display: grid;
    grid-template-columns: 2fr 4fr 3fr;
    grid-template-areas: 
    "filter-header brewerylist-header brewerylist-header"
    "filter-content brewerylist-content brewerylist-content";
    
}

.page-header{
  background-color: white;
  color: rgb(172, 13, 13);
  border-top: 1px solid rgb(172, 13, 13);
  font-size: 40px;
  font-weight: 900;
  text-align: center;
  margin-bottom: 0px;
  padding-bottom: 40px;
  padding-top: 30px;
}
#filter-header {
  grid-area: filter-header;
  border-radius: 10px 0 0 0;
  border-left: 1px solid rgb(172, 13, 13);
  text-decoration: underline;
  
}
#brewerylist-header {
  background-color: white;
  color: rgb(172, 13, 13);
  grid-area: brewerylist-header;
  border-top: 1px solid rgb(172, 13, 13);
  border-right: 1px solid rgb(172, 13, 13);
  border-radius: 0 10px 0 0;
  text-decoration: underline;
}

/* #map-header {
  background-color: rgb(255, 255, 255);
  color: rgb(172, 13, 13);
  grid-area: map-header;
  border-radius: 0 10px 0 0 ;
  border-right: 1px solid rgb(172, 13, 13);
  border-top: 1px solid rgb(172, 13, 13);
} */
#filter-content {
  background-color: white;
  grid-area: filter-content;
  padding-left: 40px;
  border-radius: 0 0 0 10px;
  border-left: 1px solid rgb(172, 13, 13);
  border-bottom: 1px solid rgb(172, 13, 13);
}
#brewerylist-content {
  background-color: white;
  grid-area: brewerylist-content;
  border-bottom: 1px solid rgb(172, 13, 13);
  border-left: 1px solid rgb(172, 13, 13);
  border-right:1px solid rgb(172, 13, 13);
}
/* #map-content {
  background-color: white;
  grid-area: map-content;
  padding-left: 40px;
  border-radius: 0 0 10px 0;
  border-right: 1px solid rgb(172, 13, 13);
  border-bottom: 1px solid rgb(172, 13, 13);
} */

</style>
