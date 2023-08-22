<template>
  <div class="add-brewery">
        <div class="labels" for= "name" id="name-label">Name: </div>
        <div class="labels" for="type" id="abv-label">ABV: </div>
        <div class="labels" for="abv" id="type-label">Type: </div>
        <div class="labels" for="description" id="description-label">Description: </div>
        <!-- <div class="labels" for="image" id="image-label">Image: </div> -->

        <div class="input-field">
          <input type="text" id="name-input" v-model="updatedBeer.name"/>
        </div>

        <div class="input-field">
          <input type="text" id="abv-input" v-model="updatedBeer.abv"/>
        </div>

         <div class="input-field">
          <input type="text" id="type-input" v-model="updatedBeer.type"/>
        </div>

        <div class="input-field">
          <textarea type="text" id="description-input" v-model="updatedBeer.description"/>
        </div>

        <!-- <div class="input-field">
          <input type="text" id="image" v-model="imageUrl"/>
        </div> -->

        <div id="submit">
          <button id="submit-button" @click.prevent="updateSelectedBeer">Submit Changes</button>
        </div>

        <div id="delete">
          <button id="delete-button" @click.prevent="deleteSelectedBeer">Delete Beer</button>
        </div>
  </div>
</template>

<script>
import BreweryService from '../../services/BreweryService';

export default {
    
    data() {
        return {
            updatedBeer: {},
        }
    },

    methods: {
      
        updateSelectedBeer() {
          if (isNaN(parseInt(this.updatedBeer.abv))) {
              alert("Please enter a valid ABV");
            } else {
                BreweryService.updateBeer(this.updatedBeer, this.updatedBeer.id)
              .then(response => {
                  if (response.status == 200) {
                      this.$store.commit('CHANGE_BREWER_PAGE_VIEW', 'brewery_beer_list');
                  }
                  
              })
              .catch(error => {
                  console.error('Error updating beer:', error);
              });
            }
        },
        deleteSelectedBeer() {
          if(confirm("Are you sure you want to delete this beer? This action cannot be undone.")) {
            BreweryService.deleteBeer(this.updatedBeer.id)
            .then( response => {
                if (response.status === 204) {
                  this.$emit('beerDeletedRefresh');
                  this.$store.commit('CHANGE_BREWER_PAGE_VIEW', 'brewery_beer_list');
                }
            })
            .catch(error => {
                console.error('Error updating beer:', error);
            });
          } 
        },
    },
    created() {
        this.updatedBeer = this.$store.state.selectedBeer;
    }    
}
</script>

<style scoped>
.add-brewery {
  background: white;
  display: grid;
  border: 1px solid rgb(172, 13, 13);
  border-left: none;
  border-radius: 0 10px 10px 10px;
  grid-template-columns: 1fr 3fr;
  grid-template-rows: 100px 100px 100px 200px 100px 100px;
  grid-template-areas: 
  "name-label name-input"
  "abv-label abv-input"
  "type-label type-input"
  "description-label description-input"
  ". buttons";
  row-gap: 20px;
  margin-bottom: 30px;
  margin-right: 40px;
}

.labels{
  display: flex;
  padding-top: 40px;
  padding-left: 10px;
  justify-content: center;
  font-size: 15px;
  font-weight: 900;

}

.input-field {
  padding: 40px;
  padding-left: 30px;
}

input {
  margin-right: 0px;
}

#name-label{
  grid-area: name-label;
}

#abv-label {
    grid-area: abv-label;
}

#type-label {
  grid-area: type-label;
}

#description-label {
  grid-area: description-label;
}


#name-input{
  grid-area: name-input;
  border-radius: 7px;
  border: 1px solid black;
  text-align: center;
}

#abv-input {
    grid-area: abv-input;
    border-radius: 7px;
    border: 1px solid black;
    text-align: center;
}

#type-input {
  grid-area: type-input;
  border-radius: 7px;
  border: 1px solid black;
  text-align: center;
}

#description-input{
  grid-area: description-input;
  width: 350px; 
  height: 100px;
  resize: none;
  border-radius: 10px;
  text-align: center;
}
input[type=text] {
  padding: 12px 20px;
}

textarea[type=text]{
  padding: 12px 20px;
}

button {
  display: flex;
  justify-self: right;
  white-space: nowrap;
  justify-content: center;
  align-items: center;
  margin-top: 80px;
  margin-right: 80px;
  border: 1px solid black;
}

#submit {
  grid-area: buttons;
}

#submit-button {
  height: 40px;
  width: 120px;
  border-radius: 7px;
  border: 1px solid black;
  justify-self: start;
  align-self: center;
  margin-left: 30px;
  margin-top: 30px;
  cursor: pointer;
  font-weight: 700;
}

#delete {
  grid-area: buttons;
  margin-left: 270px;
}

#delete-button {
  height: 40px;
  width: 120px;
  border-radius: 7px;
  border: 1px solid black;
  justify-self: start;
  margin-left: 30px;
  margin-top: 30px;
  cursor: pointer;
  background-color: rgba(255, 0, 0, 0.253);
  font-weight: 700;
  
}

</style>