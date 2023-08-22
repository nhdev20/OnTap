<template>
    <div class="card-container">
        <router-link class="brewery-link" v-for="brewery in filterBreweries" v-bind:key="brewery.id" :to="{ name: 'brewery-details', params: {id: brewery.id}}">
          <div class="brewery-card">
            <img :id="`brewer-image-${brewery.id}`" :src="getImgPath(brewery.id)" alt="">
            <div class="card-items">
              <h3> {{ brewery.name }} </h3> 
            </div> 
            <br>         
          </div>
        </router-link>
        <router-view />
    </div>
</template>

<script>

import breweryService from "../services/BreweryService";
export default {
  data() {
    return {
      imgUrl: '../assets/BreweryLogo/happy.png'
    }
  },
  created() {
    breweryService.getBreweries().then((response) => {
      this.$store.commit("GET_BREWERIES", response.data);
    });
  },
  computed: {
    breweryList() {
      return this.$store.state.breweries;
    },
    filterBreweries() {
        // return this.$store.state.filrterBreweries;
      const currentSearch = this.$store.state.currentSearch;
      const currentType = this.$store.state.currentType;

      return this.$store.state.breweries.filter(brewery => {
        const nameMatch = brewery.name.toLowerCase().includes(currentSearch.toLowerCase());
        const typeMatch = currentType === '' || brewery.breweryType === currentType;

        return nameMatch && typeMatch;
      });
    },

    // imgUrl() {
    //   return "../assets/BreweryLogo/happy.png";
    // }
    
  },
  methods: {
    getImgPath(id) {
      try {
        return require(`../assets/BreweryLogo/${id}.png`);
      } catch (error) {
      // return require('../assets/BreweryLogo/default-img.png');
      return "img not found"
      }
    },
  }

  
};
</script>

<style scoped>

ul {
  list-style-type: none;
}

.card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  
  
  
}

#brewer-image {
  max-height: 100%;
  max-width: 100%;
  justify-content: center;
  align-items: center;
  margin-top: 30px;
  padding: 0px;
  
}


.brewery-link{
  color: rgb(255, 145, 0);
  font-size: 20px;
  text-decoration: none;

}

.brewery-card {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 30px;
  border:1px solid;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0,.1);
  margin-bottom: 15px;
  transition: box-shadow .3 ease;
  margin: 15px;
  height: 315px;
  width: 360px;
  
  

}
.brewery-card:hover {
  box-shadow: 0 12px 20px rgba(0, 0, 0,.2)
}
</style>