<template>
  <div id="full-pane">
     <div id="nav-pane">
        
          <button id="beers" :class="currentPageView === 'brewery_beer_list' ? 'selected' : 'buttons'" @click="beerListPageView">My Beers</button>

          <button id="add" :class="currentPageView === 'add_beer_form' ? 'selected' : 'buttons'" @click="beerAddPageView">Add Beer</button>

          <!-- <button id="edit" class="minor-button" @click="beerEditPageView">Edit Beer</button> -->
        
          <button id="update" :class="currentPageView === 'brewery_update' ? 'selected' : 'buttons'" @click="breweryUpdatePageView">Update Brewery</button>
        
      </div>
  </div>
</template>

<script>
export default {
  methods: {
    breweryUpdatePageView() {
      return this.$store.commit('CHANGE_BREWER_PAGE_VIEW', 'brewery_update');
    },
    beerListPageView() {
      return this.$store.commit('CHANGE_BREWER_PAGE_VIEW', 'brewery_beer_list');
    },
    beerEditPageView() {
      return this.$store.commit('CHANGE_BREWER_PAGE_VIEW', 'edit_beer_form');
    },
    beerAddPageView() {
      return this.$store.commit('CHANGE_BREWER_PAGE_VIEW', 'add_beer_form');
    }
  },
  computed: {
    currentPageView () {
      return this.$store.state.brewerPageView;
    }
  }
}
</script>

<style scoped>

#nav-pane{
  height: 40%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
}
.buttons {
  width: 50%;
  height: 3rem;
  cursor: pointer !important;
  background-color: white;
  color: rgb(172, 13, 13); 
  border-radius: 10px;
  border: 1px solid rgb(172, 13, 13);
  min-width: 60px;
  max-width: 45%;
}

.buttons:hover{
  color: rgb(244,139,41);
  border-color: rgb(244,139,41);
  border-width: 2px;
  
}

.selected{
  width: 50%;
  height: 3rem;
  color: rgb(172, 13, 13);
  border-color: rgb(172, 13, 13);
  border-width: 2px;
  background-color: rgba(251,230,194);
  border-radius: 10px;
  min-width: 60px;
  max-width: 45%;
}

</style>