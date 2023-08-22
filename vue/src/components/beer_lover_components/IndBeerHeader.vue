<template>
  <div class="app-header">
    <div id="main-header">
      <div id="logo-div">
        <router-link :to="{ name: 'home' }">
          <img
            @click="defaultHomeView"
            class="nav-link"
            id="ontap-logo"
            src="../../assets/images/ontap-logo.png"
            alt="on-tap"
        /></router-link>
      </div>

      <div id="beer-name">
        <p>{{ beer.name }}</p>
      </div>
      
      <div id="flex-buttons">
        
        <div class="btn-container">
          <button
            class="menu-btn"
            @click="$router.push({ name: 'account-user' })"
          >
            Account Menu
          </button>
        </div>
        <div class="btn-container">
          <button
            class="logout-btn"
            @click="$router.push({ name: 'logout' })"
            v-if="$store.state.token != ''"
          >
            Log Out
          </button>
        </div>
        
      </div>
      
    </div>
  </div>
</template>

<script>
export default {
  props: ["beer"],
  methods: {
        defaultHomeView() {
            this.$store.commit('CHANGE_BREWER_PAGE_VIEW', 'brewery_beer_list');
        }
    }
};
</script>

<style scoped>
.app-header {
  background: rgba(251, 230, 194);
  border-top: 1px solid rgb(172, 13, 13);
  border-radius: 10px 10px 0 0;
  padding-top: 20px;
  padding-bottom: 20px;
}
#main-header {
  color: rgb(172, 13, 13);
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
  grid-template-areas: "logo title menu";
  align-items: center;
  /* justify-items: center; */
}
#logo-div {
  grid-area: logo;
  text-align: center;
}
#beer-name {
  grid-area: title;
  text-align: center;
}
.menu-btn{
    /* margin-left: 80px; */
    background-color: white;
    color: rgb(244,139,41);
    font-size: 1rem;
    height: 40px;
    width: 160px;
    border-radius: 10px;
    border: 2px solid rgb(244,139,41);
}
.logout-btn{
    /* margin-right: 60px; */
    background-color: white;
    color: rgb(172, 13, 13);
    font-size: 1rem;
    height: 40px;
    width: 160px;
    border-radius: 10px;
    border: 2px solid rgb(172, 13, 13)
}
p{
  font-size: 3.5rem;
}
#flex-buttons{
    grid-area: menu;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-between;
    align-content: center;
    grid-area: menu;
    padding: 30px;
}
.btn-container{
  text-align: center;
}
.btn:hover {
  cursor: pointer !important;
}
#ontap-logo {
  width: 180px;
  height: auto;
  
}

#beer-name {
  font-size: 40px;
}
</style>