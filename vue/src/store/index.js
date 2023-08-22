import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    breweries: [],
    filterBreweries: [],
    // curUser: currentUser,
    brewerPageView: 'brewery_beer_list',
    currentSearch: '',
    currentType: '',
    selectedBeer: '',
    updatedBeer: ''

    // curUser: currentUser,
    

  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    GET_BREWERIES(state, breweryList) {
      state.breweries = breweryList
    },
    CHANGE_BREWER_PAGE_VIEW(state, page) {
      state.brewerPageView = page;
    },
    UPDATE_BREWERY(state, updatedBrewery) {
      let breweryIdx = state.breweries.findIndex(brewery => {
        return brewery.id === updatedBrewery.id;
      });
      state.breweries[breweryIdx] = updatedBrewery;
    },
    FILTER_BREWERIES(state, currentSearch) {
      state.currentSearch = currentSearch;
    },
    UPDATE_CURRENT_TYPE(state, type) {
      state.currentType = type;
    },
    UPDATE_SELECTED_BEER(state, beer) {
      state.selectedBeer = beer;
    },  
    UPDATE_BEER_BY_ID(state, updatedBeer) {
      state.updatedBeer = updatedBeer;
    }
    
  }
})
