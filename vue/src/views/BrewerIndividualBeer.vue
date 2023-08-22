<template>
  <div id="grid">
    <app-header-brewer-account id="header" :brewery="brewery" />
    <brewer-individual-beer-details :brewery="brewery" />
    <!-- <nav-pane-brewer-account id="nav"/> -->
  </div>
</template>

<script>
import AppHeaderBrewerAccount from '../components/brewer_components/AppHeaderBrewerAccount.vue';
import BrewerIndividualBeerDetails from '../components/brewer_components/BrewerIndividualBeerDetails.vue';
import breweryService from '../services/BreweryService.js';
// import NavPaneBrewerAccount from '../components/NavPaneBrewerAccount.vue';

export default {
    data() {
        return {
            brewery: {}
        }
    },

    components: {
        AppHeaderBrewerAccount,
        BrewerIndividualBeerDetails,
        // NavPaneBrewerAccount
    },

    created() {
        breweryService.getBreweries().then((response) => {
            this.$store.commit("GET_BREWERIES", response.data);
            this.brewery = this.$store.state.breweries.find( curBrewery => {
                return curBrewery.brewerId === this.$store.state.user.id;
            });
            breweryService.getBeers(this.brewery.id).then( response => {
                this.beers = response.data;
            });
        });
        
    },

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

</style>