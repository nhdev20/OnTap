<template>
  <div id="whole-page">
      <ind-beer-header :beer="beer"/>
      
      <div id="main-space" >
          <div id="left-grid">
              <ul>
                  <div v-show="showAttributes">
                    <li class="labels" id="beerType" >Beer Type: {{beer.type}} </li>
                    <li class="labels" id="beerAbv"> ABV: {{beer.abv}}%</li>
                    <li class="label" id="beerDescription"> 
                        "{{beer.description}}"</li>
                  </div>
                  <div v-show="showRating">
                      <li>Average Rating: {{beerAvgRating}}/5 </li>
                  </div>
                  
                </ul>
                
                <select id="dropdown" v-model="rating.amount">
                    <option value=""></option>
                    <option value=1>1</option>
                    <option value=2>2</option>
                    <option value=3>3</option>
                    <option value=4>4</option>
                    <option value=5>5</option>
                </select>
                <br>
                <button id="submitrating" type="button" @click="submitRating()">Submit Rating</button>
          </div>
          <div id="right-grid">
              <div class="review-box">
                 <div id="newReview" v-for="review in reviews" :key="review.userId">
                  "{{review.description}}"
                </div>
              </div>  
              <textarea id="reviewarea" rows="5" cols="50" v-model="newReview.description" placeholder= " Submit review here...">  </textarea>
              <br
              >
              <button id="sumbitreviewbutton" type="button" @click="submitReview()">Submit Review</button>
              
             </div>
      </div>
  </div>
</template>

<script>
import IndBeerHeader from '../components/beer_lover_components/IndBeerHeader.vue'
import breweryService from '../services/BreweryService.js'
import ratingReviewService from '../services/RatingReviewService.js'

export default {
    components: {
        IndBeerHeader
    }, 
    created() {
        breweryService.getBeer(this.$route.params.id)
            .then( response => {
                this.beer = response.data;
            });
        ratingReviewService.getBeerAvgRating(this.$route.params.id)
            .then( response => {
                this.beerAvgRating = response.data;
                this.showAttributes = true;
                this.showRating = true;
            })
            .catch( error => {
                if (error.response) {
                    this.errorFlag = true;
                }
                this.showAttributes = true;
            })
        ratingReviewService.getBeerReviews(this.$route.params.id)
            .then( response => {
                this.reviews = response.data;
            })
    },
    data () {
        return {
            beer: {},
            beerAvgRating: 0,
            showAttributes: false,
            showRating: false,
            rating: {
                userId: this.$store.state.user.id,
                beerId: this.$route.params.id,
                amount: ''
            },
            newReview: {
                userId: this.$store.state.user.id,
                beerId: this.$route.params.id,
                description: ''
            }, 
            reviews: [], 
            errorFlag: false
        }
    }, 
    methods: {
        submitRating() {
            if (this.rating.amount === '') {
                alert('Please select a valid rating');
            } else {
                ratingReviewService.addBeerRatings(this.rating)
                .then(response => {
                    if(response.status == 201) {
                        this.$router.push({name: 'account-user'});
                    }
                })
                .catch((error) => {
                    if (error.response && error.response.status === 409) {
                        alert('You have already rated this beer!');
                    }
                })
            }

            
        },
        submitReview() {
            ratingReviewService.addBeerReview(this.newReview)
            .then (response => {
                if (response.status == 201){
                    this.$router.push({name: 'account-user'})
                }
            })
            .catch((error) => {
                if (error.response && error.response.status === 409) {
                    alert('You have already reviewed this beer!');
                }
            })
         
        }
    }
}
</script>

<style scoped>

#whole-page{
    background: rgba(251,230,194);
    border: 1px solid rgb(172, 13, 13);
    border-top: none;
    border-radius: 10px;
    height: 100;
}
#main-space{
    background: white;
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-areas: 
    "left right";
    border: 1px solid rgb(172, 13, 13);
    border-radius: 10px;
    margin-left: 30px;
    margin-right: 30px;
    margin-bottom: 40px;
}

#newReview {
    margin-bottom: 20px;
    margin-top: 20px;
    /* padding-top: 40px; */
}
#left-grid{
    grid-area: left;
    display: flexbox;
    margin-right: 40px;
    margin-bottom: 30px;
    font-size: 20px;
}
#right-grid{
    grid-area: right;
    /* display: flexbox; */
    display: flexbox;
    flex-wrap: wrap;
    padding: 10px;
    font-size: 20px;
}
li {
    padding: 10px;
    list-style-type: none;
    
}
#beerType {
   text-decoration-style: dotted;
}
#dropdown {
    border-left-width: 1px;
    padding-left: 16px;
    margin-left: 50px;
}
#submitrating {
    
    padding: 2px;
    margin-left: 50px;
    margin-top: 20px;
   
}
#reviewarea {
    margin-top: 70px;
    margin-right: 40px;
    margin-bottom: 1px;
    font-size: 1rem;
    display: flex;
    align-content: left;
    justify-content: center;
    resize: none;
}
#sumbitreviewbutton {
    display: flex;
    align-content: center;
    justify-items: center;
    margin-bottom: 20px;
    
}
/* .beer-filling {
  width: 100px;
  height: 100px;
} */


</style>