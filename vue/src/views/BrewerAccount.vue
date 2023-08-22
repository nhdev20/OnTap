<template>
  <div id="grid">
      <app-header-brewer-account id="header" :brewery="brewery" />
      <nav-pane-brewer-account id="nav"/>
      <brewer-beer-list id="beer-list" v-if="isBreweryBeerList" :beers="beers" :ratings="ratings"/>
      <add-beer-form id="add-beer" v-if="isAddBeerForm" :brewery="brewery"/>
      <brewery-update id="update-brewery" :brewery="brewery" v-if="isBreweryUpdate"/>
      <brewer-individual-beer-details id="brewer-beer-details" :beer="selectedBeer" v-if="isBrewerBeerDetails" />
      <edit-beer-form id="brewer-beer-edit" :beer="selectedBeer" v-if="isBrewerBeerEdit" @beerDeletedRefresh="getBeers" />
  </div>
</template>

<script>
import AppHeaderBrewerAccount from '../components/brewer_components/AppHeaderBrewerAccount.vue';
import BrewerBeerList from '../components/brewer_components/BrewerBeerList.vue';
import NavPaneBrewerAccount from '../components/brewer_components/NavPaneBrewerAccount.vue';
import BreweryUpdate from '../components/brewer_components/BreweryUpdate.vue';
import AddBeerForm from '../components/brewer_components/AddBeerForm.vue';
import BrewerIndividualBeerDetails from '../components/brewer_components/BrewerIndividualBeerDetails.vue';
import EditBeerForm from '../components/brewer_components/EditBeerForm.vue';
import breweryService from '../services/BreweryService.js';
// import ratingReviewService from '../services/RatingReviewService.js';



export default {
    data () {
        return {
            brewery: {},
            beers: [],
            ratings: [],
        }
    },
    components: {
        AppHeaderBrewerAccount,
        NavPaneBrewerAccount,
        BrewerBeerList,
        BreweryUpdate,
        AddBeerForm,
        BrewerIndividualBeerDetails,
        EditBeerForm
    },
    created() {
        breweryService.getBreweries().then((response) => {
            this.$store.commit("GET_BREWERIES", response.data);
            this.brewery = this.$store.state.breweries.find( curBrewery => {
                return curBrewery.brewerId === this.$store.state.user.id;
            });
            this.getBeers();
            // ratingReviewService.getRatingsByUser(this.$store.state.curUser.id).then( response => {
            //     this.ratings = response.data;
            // })
            
        });
        
    },
    methods: {
        getBeers() {
            breweryService.getBeers(this.brewery.id).then( response => {
                this.beers = response.data;
            });
        }
    },
    computed: {
        isBreweryBeerList() {
            return this.$store.state.brewerPageView === 'brewery_beer_list';
        },
        isBreweryUpdate() {
            return this.$store.state.brewerPageView === 'brewery_update';
        },
        isEditBeerForm() {
            return this.$store.commit.brewerPageView === 'edit_beer_form';
        },
        isAddBeerForm() {
            return this.$store.state.brewerPageView === 'add_beer_form';
        },
        isBrewerBeerDetails() {
            return this.$store.state.brewerPageView === 'brewer_beer_details';
        },
        isBrewerBeerEdit() {
            return this.$store.state.brewerPageView === 'brewer-beer-edit';
        },
        selectedBeer() {
            return this.$store.state.selectedBeer;
        }
    }
}
</script>

<style scoped>


#grid{
    background-color: rgba(251,230,194);
    border-left: 1px solid rgb(172, 13, 13);
    border-right: 1px solid rgb(172, 13, 13);
    border-bottom: 1px solid rgb(172, 13, 13);
    border-radius: 10px 10px 10px 10px;
    display: grid;
    grid-template-rows: 1fr 4fr;
    grid-template-columns: 1fr 4fr;
    grid-template-areas:
    "header header"
    "nav main";
    
}
#header{
    grid-area: header;
}
#nav{
    grid-area: nav;
    background: rgba(172, 13, 13, 0.2);
    border: 1px solid rgb(172, 13, 13);
    border-right-width: 4px;
    margin-left: 40px;
    border-radius: 10px 0 0 10px;
    margin-bottom: 30px;
    
}
#beer-list{
    grid-area: main;
    margin-right: 40px;
    margin-bottom: 30px;
}
#update-brewery{
    grid-area: main;
}

#brewer-beer-details {
    grid-area: main;
}
</style>