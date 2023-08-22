<template>
  <div class="add-brewery">
        <div class="labels" for= "name" id="name-label">Name: </div>
        <div class="labels" for="abv" id="abv-label">ABV: </div>
        <div class="labels" for="type" id="type-label">Type: </div>
        <div class="labels" for="description" id="description-label">Description: </div>
        <!-- <div class="labels" for="image" id="image-label">Image: </div> -->

        <div class="input-field">
          <input type="text" id="name-input" v-model="newBeer.name"/>
        </div>

        <div class="input-field">
          <input type="text" id="abv-input" v-model="newBeer.abv"/>
        </div>

         <div class="input-field">
          <input type="text" id="type-input" v-model="newBeer.type"/>
        </div>

        <div class="input-field">
          <textarea id="description-input" v-model="newBeer.description"/>
        </div>

        <!-- <div class="input-field">
          <input type="text" id="image" v-model="imageUrl"/>
        </div> -->
        <div class="submit-button">
          <button id="submit" @click.prevent="addBeer">Add Beer</button>
        </div>
  </div>
</template>

<script>
import BreweryService from '../../services/BreweryService'
export default {
    props: ['brewery'],
    data() {
        return {
            newBeer: {
                name: '',
                description: '',
                abv: '',
                type: ''
            }
        }
    },
    methods: {
        addBeer() {
            if (isNaN(parseInt(this.newBeer.abv))) {
              alert("Please enter a valid ABV");
            } else {
              BreweryService.addBeer(this.newBeer, this.brewery.id)
                  .then(response => {
                      if (response.status == 201) {
                          this.$router.push({name: 'home'});
                      }
                  })
            }


        }
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
  grid-template-rows: 100px 100px 100px 100px;
  grid-template-areas: 
  "name-label name-input"
  "abv-label abv-input"
  "type-label type-input"
  "description-label description-input"
  ". button";
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

/* #image-label {
  grid-area: image-label;
} */
input[type=text] {
  padding: 12px 20px;
}

textarea[type=text]{
  padding: 12px 20px;
}

#name-input{
  grid-area: name-input;
  border-radius: 7px;
  border: 1px solid black;
}

#abv-input {
    grid-area: abv-input;
    border-radius: 7px;
    border: 1px solid black;
}

#type-input {
  grid-area: type-input;
  border-radius: 7px;
  border: 1px solid black;
}

#description-input {
  grid-area: description-input;
  width: 350px; 
  height: 100px;
  resize: none;
  border-radius: 10px;
  border: 1px solid black;
}

button {
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

.submit-button {
  grid-area: button;
  margin-top: 60px;
}



/* 
#image {
  grid-area: image-input;
} */

</style>