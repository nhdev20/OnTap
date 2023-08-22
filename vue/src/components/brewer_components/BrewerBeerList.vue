<template>
  <div class="brewery-beer-list">
    
    <a class="beer-links" v-for="beer in beers" v-bind:key="beer.id" @click="routeToDetails(beer)">
      <div class="data-div">
        <!-- <div v-for="rating in ratings" :key="rating" id="image-div">
          <img
          class="beer-rating"
          v-for="index in rating"
          v-bind:key="index"
          src="../assets/images/beer-rating.png"
          />
        </div> -->
        <span class="beer-name" id="beer-name">{{ beer.name }}</span>

      </div>
    </a>
      
  </div>
</template>

<script>
import ratingReviewService from '../../services/RatingReviewService.js';

export default {
  props: ["beers"],
  data() {
    return {
      ratings: [],
    }
  },
  created() {
    for (const beer of this.beers) {
      ratingReviewService.getBeerAvgRating(beer.id)
        .then( response => {
            this.ratings.push(response.data);
        })
        .catch( error => {
            if (error.response) {
                this.errorFlag = true;
            }
        })
    }
  },
  computed: {
    numberOfBeers() {
      return this.beers.length;
    },
  },
  methods: {
    routeToDetails(selectedBeer) {
      this.$store.commit('UPDATE_SELECTED_BEER', selectedBeer);
      this.$store.commit('CHANGE_BREWER_PAGE_VIEW', 'brewer_beer_details');
    },
  }
};
</script>

<style scoped>
.brewery-beer-list {
  background: white;
  border: 1px solid rgb(172, 13, 13);
  border-left: none;
  border-radius: 0 10px 10px 0;
  padding: 2rem;
}
.beer-rating {
  grid-area: beer-ratings;
}

.beer-name {
  display: flex;
  align-items: center;
  justify-content: center;
  grid-area: beer-list;
  font-size: 2rem;
  color: rgb(244,139,41);
  padding: 10px;
}
img {
  width: 30px;
}
/* .data-div {
  display: grid;
  grid-template-columns: 1fr 1.5fr 2.5fr 1fr;
  grid-template-areas: ". image beer-name .";
  
  text-align: center;
  align-items: center;
} */

#image-div {
  grid-area: image;
  /* text-align: center; */
}

#btn {
  grid-area: btn;
}

#beer-name {
  grid-area: beer-name;
}

.beer-links {
  text-decoration: none;
}

a {
  cursor: pointer;
}



</style>