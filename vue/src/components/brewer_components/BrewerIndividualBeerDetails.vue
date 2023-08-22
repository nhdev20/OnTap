<template>
    <div>
        <div class="header">
            <h1 id="beer-name"> {{beer.name}} </h1>
            <button id="edit-btn" @click="switchToEdit()">Edit</button>
        </div>
        <div class="beer-details">
            <div class="left-form">
                <div class="keys"> Beer Rating: </div>
                <br> 
                <div id="ratingIcons">
                        <img
                        class="beer-rating"
                        v-for="n in averageRating" 
                        :key="n"
                        src="../../assets/images/beer-rating.png"
                        />
                    </div>
                <div class="left-form-details" v-for="(value, key) in beer" v-bind:key="key" v-show="confirmNotId(key)">
                    <span class="keys">{{`${formatKey(key)}: `}}</span>
                    <br>
                    <span class="values">{{value}}</span>
                    
                </div>
            </div>

            <div class="right-form"> 
                <div id="review-header">Reviews:</div>
                <div class="review-div" v-for="review in selectedBeerReviews" v-bind:key="review.id">
                    <ul id='review-list'> 
                        <li id="reviews" v-for="(value, key) in review" :key="key" :class="{'hidden': !filteredReviewData(key)}">
                            <span>" {{value}} "</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import ratingReviewService from '../../services/RatingReviewService';
export default {
    props: ["beer"],
    data() {
        return {
            averageRating: '',
            selectedBeerReviews: []
        }
    },
    created() {
        ratingReviewService.getBeerAvgRating(this.beer.id)
            .then(response => {
                if (response.status == 200) {
                    this.averageRating = response.data;
                }
            });
        ratingReviewService.getBeerReviews(this.beer.id)
            .then(response => {
                if (response.status == 200) {
                    this.selectedBeerReviews = response.data;
                }
            });
    },

    methods: {
        formatKey(key) {
            return String(key).replace(/([a-z])([A-Z])/g, '$1 $2').replace(/([a-z])([1-9])/g, '$1 $2');
        },
        confirmNotId(key) {
            return key != 'id';
        },
        filteredReviewData(key) {
            return key != 'beerId' && key != 'userId';
        },
        switchToEdit() {
            this.$store.commit('CHANGE_BREWER_PAGE_VIEW', 'brewer-beer-edit')
        }
    }
};
</script>

<style scoped>

.hidden {
    display: none;
}

img{
    width: 30px;
    margin-left: 20px;
}

#review-list {
    list-style: none;
    
}
#ratingIcons{
    display: inline-flex;
    width: 30px;
    padding-left: 10px;
}
#reviews {
    padding-bottom: 20px;
    
    
}
.review-div {
    margin-right: 30px;
    
}
.header {
    display: flex;
    /* justify-content: center; */
    padding-left: 420px;
    align-content: center;
    border: 1px solid rgb(172, 13, 13);
    border-bottom: none;
    border-left: none;
    background-color: white;
    margin-right: 40px;
    border-radius: 0 10px 0 0;
}
.values {
    padding-left: 30px;
}

.left-form-details {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    margin-bottom: 40px;
    margin-top: 10px;
    margin-right: 40px;
}

#edit-btn {
    
    align-self: center;
    width: 50%;
    height: 30px;
    cursor: pointer !important;
    background-color: white;
    color: rgb(172, 13, 13); 
    border-radius: 10px;
    border: 2px solid rgb(172, 13, 13);
    min-width: 40px;
    max-width: 5%;
    margin-top: 10px;
    margin-left: 50px;
    font-weight: 700;
}
.beer-details {
    background: white;
    display: grid;
    border: 1px solid rgb(172, 13, 13);
    border-top: none;
    border-left: none;
    border-radius: 0 0 10px 10px;
    grid-template-columns: 1fr 1.3fr;
    grid-template-areas: "left-form right-form";
    row-gap: 20px;
    column-gap: 20px;
    margin-right: 40px;
    margin-bottom: 30px;
    
}

.left-form {
    display: flex;
    flex-direction: column;
    grid-area: left-form;
    padding-left: 30px;
    margin-right: 10px;
    max-height: 100%;
    border-right: 1px solid rgb(172, 13, 13);

}

.right-form {
    grid-area: right-form;
    
    margin-right: 10px;
    max-height: 100%;
}

.keys {
    text-transform: capitalize;
    font-weight: bold;
}
#review-header {
    text-align:left;
    margin-top: 0px;
    font-weight: bold;

}

#edit-btn:hover{
  color: rgb(244,139,41);
  border-color: rgb(244,139,41);
  border-width: 2px;
  
}


</style>