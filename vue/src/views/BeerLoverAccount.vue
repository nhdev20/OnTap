<template>
  <div class="beer-lover-account">
      <beer-lover-header id="beer-lover-header" />
      <div id="main">
          <div class="left-form"> 
          <div class="headers" id="rating-header">Ratings</div>
          <div id="ratings">
                <div id="individual-beer" v-for="element in ratingsAndBeers" :key="element.beerId">
                    <div id="category">Beer Name: </div>
                    <span id="info">{{element.name}}</span>
                    <div id="category">Type of Beer: </div>
                    <span id="info">{{element.type}}</span>
                     <div id="category">My Rating: </div>
                    <span id="info" >{{element.rating}}/5  </span>
                    <br>
                    <!-- do we want these on the same line and make them bigger font? -->
                </div> 
            </div>
          </div>
          <div class="right-form">
          <div class="headers" id="review-header">Reviews</div>
          <div id="reviews">
              <div id="review" v-for="element in reviewsAndBeers" :key="element.beerId">
                    <div id="review-name-head">Beer Name: </div>
                    <span id="review-name"> {{element.name}}</span>
                    <div id="review-type-head"> Beer Type: </div>
                    <span id="review-type"> {{element.type}}</span>
                    <div id="review-review"> Review: </div>
                    <span id="review-description">{{element.review}}</span>
                </div>
            </div>
          </div>
      </div>
  </div>
</template>

<script>
import BeerLoverHeader from '../components/beer_lover_components/BeerLoverHeader.vue'
import ratingReviewService from '../services/RatingReviewService.js'


export default {
  components: { BeerLoverHeader },
    data() {
        return {
            ratingsAndBeers: [],
            reviewsAndBeers: []
        }
    },
    created() {
        ratingReviewService.getRatingsAndBeersByUser(this.$store.state.user.id)
            .then( response => {
                this.ratingsAndBeers = response.data;
                
            });
        ratingReviewService.getReviewsAndBeersByUser(this.$store.state.user.id)
            .then( response => {
                this.reviewsAndBeers = response.data;
                
            })
    }
}
</script>

<style scoped>

div#review {
    display: flex;
    flex-direction: column;
    border: 2px solid rgb(172, 13, 13);
    margin-bottom: 20px;
    border-radius: 10px 10px 10px 10px;
    padding-left: 10px;
    padding-bottom: 10px;
    padding-right: 10px;
}

div#review-name-head {
    color: rgb(172, 13, 13);
}

div#review-type-head {
    color: rgb(172, 13, 13);
}

div#review-review {
    color: rgb(172, 13, 13);
}

span#review-description {
    /* border: 1px solid black; */
    font-size: 14px;
}

div#individual-beer {
    border: 2px solid rgb(172, 13, 13);
    margin-bottom: 20px;
    border-radius: 10px 10px 10px 10px;
    padding-bottom: 10px;
     box-shadow: 0 4px 8px rgba(0, 0, 0,.1);
}
div#individual-beer:hover {
    box-shadow: 0 12px 20px rgba(0, 0, 0,.2)

}

div#category {
    padding-left: 30px;
    color: rgb(172,13,13);
    /* text-decoration: underline; */
    
}

span#info {
    padding-bottom: 20px;
    color: black;
    padding-left: 60px;
}

.beer-lover-account{
    background: rgba(251,230,194);
    height: 100%;
    
}
#main {
    height: 100%;
    background: rgba(251,230,194);
    border: 1px solid rgb(172, 13, 13);
    border-top: none;
    border-radius: 0 0 10px 10px;
    display: grid;
    grid-template-columns: .75fr 1fr;
    grid-template-areas: 
    "left-form right-form";
}

.left-form{
    background: white;
    display: flex;
    flex-direction: column;
    grid-area: left-form;
    margin-left: 30px;
    max-height: 100%;
    border: 1px solid rgb(172, 13, 13);
    border-radius: 10px 0 10px 10px;
    margin-bottom: 30px;
    padding: 10px
}

.right-form{
    background: white;
    display: flex;
    flex-direction: column;
    grid-area: right-form;
    margin-right: 30px;
    max-height: 100%;
    border: 1px solid rgb(172, 13, 13);
    border-radius: 0 10px 10px 10px;
    border-left: none;
    margin-bottom: 30px;
    padding: 10px;
}
#ratings {
    grid-area: ratings;
    padding-top: 30px;
    padding-left: 30px;
    padding-right: 20px;
}
#reviews {
    grid-area: review;
    padding-top: 30px;
    padding-left: 30px;
    padding-right: 20px;
}

#rating-header, #review-header{
    display: flex;
    justify-content: center;
    font-size: 40px;
    font-weight: 900;
    color: rgb(172, 13, 13);
}
/* 
#individual-beer{
    padding-bottom: 20px;
    /* border: 1px solid rgb(172, 13, 13); */
/* } */ 

#info{
    padding-left: 100px;
}
</style>