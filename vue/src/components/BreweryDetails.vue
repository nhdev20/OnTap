<template>
    <div class="brewery-details">    
        
        <div id="brewery-banner" class="banner">
            <h1> {{selectedBrewery.name}} </h1> 
        </div>
            
        <div id="content-flex">
            <div id="details-content"> 
                    <!-- <h2> Brewery Details</h2> -->
                <div class="head-text">Brewery Details</div>
                <ul>
                    <li v-for="(value, key) in filteredDetails" v-bind:key="key" :class="{'hidden': value === null}">
                        <span class="keys">{{`${formatKey(key)}: `}}</span>
                        <span v-show="!valueIsArray(value)">{{ value != null ? value : null }}</span>
                        <ul v-show="valueIsArray(value) && keyIsBeerList(key)">
                            <li v-for="(arrObject, index) of value" v-bind:key="index">
                                <ul>
                                    <li v-for="(objectValue, objectKey) in arrObject" v-show="confirmNotId(objectKey)" v-bind:key="objectKey">
                                        <span class="keys"> {{`${formatKey(objectKey)}: `}} </span>
                                        <router-link v-if="isBeerName(key, objectKey)" :to="{name: 'beer-details', params: {id: arrObject.id}}" class="link">{{objectValue}}</router-link>
                                        <span v-if="!(isBeerName(key, objectKey))"> {{ objectValue }} </span>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <ul v-show="valueIsArray(value) && keyIsHoursOfOperation(key)">
                            <li v-for="(arrObject, index) of value" v-bind:key="index">
                                <span class="keys"> {{ `${arrObject.day} : ${arrObject.openStatus === true ? '' : 'Closed'}` }} </span>
                                {{ `${arrObject.openStatus === true ? `${arrObject.startTime}:00 ${arrObject.startAmPm}` : ''}`}}
                                {{ `${arrObject.openStatus === true ? `- ${arrObject.endTime}:00 ${arrObject.endAmPm}` : ''}`}}
                            </li>
                        </ul>
                        <p></p>
                    </li>
                </ul>
            </div>    
            
            <div id="map-label">
                <div id="map-border">
                    <div class="head-text">Map</div>
                    <div id="map-content">
                        <brewery-map :latitude="selectedBrewery.latitude" :longitude="selectedBrewery.longitude" :address="combinedAddress"></brewery-map>
                    </div>
                </div>
            </div>    
        </div> 
       

    </div>


</template>

<script>
import breweryService from '../services/BreweryService';
import BreweryMap from './BreweryMap.vue';
export default {
    components: {
        BreweryMap
    },
    data() {
        return {
            hoursOfOperation: [],
            selectedBrewery: {}
        }    
    },

    // computed: {
    // RETRIEVE DIRECTLY FROM STORE

    //     selectedBrewery() {
    //         return this.$store.state.breweries.find(brewery => {
    //             return brewery.id === this.$route.params.id;
    //         });
    //     }
    // }


    // RETRIEVE FROM DATABASE
    created() {
        breweryService.getBreweryById(this.$route.params.id)
            .then(resp => {
                if (resp.status === 200) {
                    this.selectedBrewery = resp.data;
                }
            });
    },
    computed: {
        filteredDetails() {
            let filtered = {};
            let removedItems = ['id', 'brewerId', 'name', 'openDbId', 'latitude', 'longitude'];
            for (const key in this.selectedBrewery) {
                if (!removedItems.includes(key)) {
                    filtered[key] = this.selectedBrewery[key];
                }
            }
            return filtered;
        },
        combinedAddress() {
            return `${this.selectedBrewery.streetAddress1}, ${this.selectedBrewery.city} ${this.selectedBrewery.stateProvince}, ${this.selectedBrewery.postalCode}`;
        },
        // determineValueComputed(value) {
        //     if (value == null) return '';
        //     if (typeof value == 'object') {
        //         let returnValues = [];
        //         for (const key in value) {
        //             returnValues.push(key[value]);
        //         }
        //         return returnValues;
        //     }
        //     return value;
        // }
    },
    methods: {
        determineValueMethod(value) {
            if (value == null) return '';
            if (Array.isArray(value)) {
                let valueArray = value;
                let returnValues = [];
                for (const object of valueArray) {
                    for (const key in object) {
                        returnValues.push(`${key}, ${object[key]}`)
                    }
                }
                return returnValues;
            }
            return value;
        },
        valueIsArray(value) {
            return Array.isArray(value);
        },
        keyIsBeerList(key) {
            return key === 'beerList';
        },
        keyIsHoursOfOperation(key) {
            return key === 'hoursOfOperation';
        },
        formatKey(key) {
            return String(key).replace(/([a-z])([A-Z])/g, '$1 $2').replace(/([a-z])([1-9])/g, '$1 $2');
        },
        confirmNotId(key) {
            return key != 'id';
        },
        isBeerName(key, objectKey) {
            return key === 'beerList' && objectKey === 'name';
        }
    }
}
</script>

<style scoped>
.hidden{
    display: none;
}
.link{
    color: rgb(244,139,41);
}
#brewery-banner {
    background-image: url("../assets/brewery-banner.jpg");
    background-position: 60% 85%;
    filter: brightness(90%);
    border: 1px solid black;
    height: 300px;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    
}

h1 {
    margin-left: 400px;
    margin-block-start: 100px;
    font-size: 80px;
    color: white;
}
.brewery-details {
  background: rgba(251,230,194, .7);
  display: grid;
  border: 1px solid rgb(172, 13, 13);
  border-radius: 10px;
  grid-template-columns: 1fr;
  grid-template-areas: 
    "banner"
    "content"
    ;
  
}
#content-flex{
    grid-area: content;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-around;
    /* padding-right: 100px; */
    padding-left: 40px;
    padding-right: 40px;
}
#details-content {

  color: rgb(172, 13, 13);
  
  /* margin-left: 100px; */
  border: 1px solid rgb(172, 13, 13);
  border-radius: 10px 0 0 10px;
  background: white;
  overflow-y: scroll;
  height: 900px;
  width: 900px;
  margin-top: 40px;
  
}
#map-label {
  color: rgb(172, 13, 13);
  margin-top: 40px;
}
.head-text {
    background: white;
    font-size: 40px;
    font-weight: 900;
    padding-left: 40px;
    padding-top: 30px;
    border-radius: 10px;
}

.keys {
    text-transform: capitalize;
    font-weight: bold;
    color: rgb(172, 13, 13);
}
ul {
    font-size: 20px;
    list-style-type: none;
    margin-top: 30px;
    margin-left: 20px;
}



#map-border{
    border: 1px solid rgb(172, 13, 13);
    border-radius: 10px;
}
.banner {
  grid-area: banner;
}

#map-content {
  
  background: white;
  display: flex;
  justify-content: center;
  overflow: hidden;
  min-width: auto;
  min-height: auto;
  padding: 30px;
  border-radius: 10px;
  /* padding-top: 30px;
  padding-bottom: 30px; */
  /* background-size: cover; */
}

</style>