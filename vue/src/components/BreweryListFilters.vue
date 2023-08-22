<template>
  <div class="filter-container">
    <div class="filter-name">
        <input type="text" id="filter-name" v-model="searchQuery" @input="filterBreweries" placeholder="Brewery name"/>
        
    </div>
    
    <div class="filter-brewery-type" >
            <select id="brewery-type" v-model="currentType" @change="updateType(currentType)" placeholder="Text">
                <option value=""></option>
                <option value="">All</option>
                <option value="bar">Bar</option>
                <option value="brewpub">Brewpub</option>
                <option value="closed">Closed</option>
                <option value="contract">Contract</option>
                <option value="large">Large</option>
                <option value="micro">Micro</option>
                <option value="nano">Nano</option>
                <option value="planning">Planning</option>
                <option value="proprietor">Proprietor</option>
                <option value="regional">Regional</option>
            </select>
    </div>
        
        
    
  </div>
</template>

<script>
export default {
    data() {
        return {
            searchQuery: '',
            currentType: '',
            
        }
    },
    methods: {
        filterBreweries() {
            this.$store.commit('FILTER_BREWERIES', this.searchQuery);
        },
        updateType(type) {
            this.$store.commit('UPDATE_CURRENT_TYPE', type);
        }
        
    }

}
</script>

<style scoped>
.filter-container {
    margin-top: 30px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    
}
#brewery-type {
    width: 308px;
    height: 35px;
    margin-bottom: 30px;
}
#filter-name {
    justify-items: center;
    width: 300px;
    height: 30px;
}

#filter-location {
    justify-items: center;
    width: 300px;
    height: 30px;

}

.filter-name , .filter-location, #brewery-type-filter {
    margin-bottom: 30px;
    margin-right: 30px;
}


</style>