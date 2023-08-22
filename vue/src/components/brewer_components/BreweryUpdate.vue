<template>
  <div class="brewery-update">
    <!-- <form> -->
    <div class="left-form">
      <div class="labels" for="name" id="name-label">Name:</div>
      <div class="labels" for="type" id="type-label">Brewery Type:</div>
      <div class="labels" for="phone" id="phone-label">Phone:</div>
      <div class="labels" for="website" id="website-label">Website:</div>
      <div class="labels" for="hours" id="hours-label">Hours of Operation: </div>

      <div class="input-field">
        <input type="text" id="name" v-model="updatedBrewery.name" />
      </div>

      <div class="input-field">
        <select name="type" id="type" v-model="updatedBrewery.breweryType">
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

      <div class="input-field">
        <input type="text" id="phone" v-model="updatedBrewery.phoneNumber" />
      </div>

      <div class="input-field">
        <input type="text" id="website" v-model="updatedBrewery.website" />
      </div>

      <div class="hours-container">
        <div
          :id="`hours-of-operation-${n}`"
          class="hours-of-operation"
          v-for="n in 7"
          :key="n"
        >
          <div class="weekday">
            {{ updatedBrewery.hoursOfOperation[n - 1].day }}
          </div>
          <div
            class="labels open-checkbox-label"
            :for="`${updatedBrewery.hoursOfOperation[n - 1].day}-checkbox`"
          >
            Open:
          </div>
          <input
            class="open-checkbox"
            type="checkbox"
            :id="`${updatedBrewery.hoursOfOperation[n - 1].day}-checkbox`"
            value="true"
            v-model="updatedBrewery.hoursOfOperation[n - 1].openStatus"
          />
          
          <div
            class="labels open-label"
            :class="{'hidden': !(updatedBrewery.hoursOfOperation[n-1].openStatus)}"
            :for="`${updatedBrewery.hoursOfOperation[n - 1].day}-open-select`"
          >
            Open:
          </div>
          <input
            class="open-time"
            :class="{'hidden': !(updatedBrewery.hoursOfOperation[n-1].openStatus)}"
            type="number"
            min="1"
            max="12"
            :id="`${updatedBrewery.hoursOfOperation[n - 1].day}-open-number`"
            v-model="updatedBrewery.hoursOfOperation[n - 1].startTime"
          />
          <select
            class="open-am-pm"
            :class="{'hidden': !(updatedBrewery.hoursOfOperation[n-1].openStatus)}"
            :id="`${updatedBrewery.hoursOfOperation[n - 1].day}-open-am-pm`"
            v-model="updatedBrewery.hoursOfOperation[n - 1].startAmPm"
          >
            <option value="AM">AM</option>
            <option value="PM">PM</option>
          </select>

          <span
            class="close-label"
            :class="{'hidden': !(updatedBrewery.hoursOfOperation[n-1].openStatus)}"
            :for="`${updatedBrewery.hoursOfOperation[n - 1].day}-close-select`"
            >Close:</span
          >
          <input
            type="number"
            min="1"
            max="12"
            class="close-time"
            :class="{'hidden': !(updatedBrewery.hoursOfOperation[n-1].openStatus)}"
            :id="`${updatedBrewery.hoursOfOperation[n - 1].day}-close-number`"
            v-model="updatedBrewery.hoursOfOperation[n - 1].endTime"
          />
          <select
            :id="`${updatedBrewery.hoursOfOperation[n - 1].day}-close-am-pm`"
            class="close-am-pm"
            :class="{'hidden': !(updatedBrewery.hoursOfOperation[n-1].openStatus)}"
            v-model="updatedBrewery.hoursOfOperation[n - 1].endAmPm"
          >
            <option value="AM">AM</option>
            <option value="PM">PM</option>
          </select>
          
        </div>
      </div>
    </div>

    <div class="right-form">
      <div class="labels" for="street1" id="street1-label">Street 1:</div>
      <div class="labels" for="street2" id="street2-label">Street 2:</div>
      <div class="labels" for="city" id="city-label">City:</div>
      <div class="labels" for="state" id="state-label">State:</div>
      <div class="labels" for="zip" id="zip-label">Zip:</div>
      <div class="labels" for="history" id="history-label">History:</div>

      <div class="input-field">
        <input
          type="text"
          id="street1"
          v-model="updatedBrewery.streetAddress1"
        />
      </div>

      <div class="input-field">
        <input
          type="text"
          id="street2"
          v-model="updatedBrewery.streetAddress2"
        />
      </div>

      <div class="input-field">
        <input type="text" id="city" v-model="updatedBrewery.city" />
      </div>

      <div class="input-field">
        <input type="text" id="state" v-model="updatedBrewery.stateProvince" />
      </div>

      <div class="input-field">
        <input type="text" id="zip" v-model="updatedBrewery.postalCode" />
      </div>

      <div class="history-field">
        <textarea id="history" v-model="updatedBrewery.history"></textarea>
      </div>

      <button id="submit" @click.prevent="updateBrewery">Submit Changes</button>
    </div>
  </div>
</template>

<script>
import breweryService from "../../services/BreweryService.js";
export default {
  props: ["brewery"],
  data() {
    return {
      updatedBrewery: {
        id: this.brewery.id,
        brewerId: this.brewery.brewerId,
        name: this.brewery.name,
        breweryType: this.brewery.breweryType,
        phoneNumber: this.brewery.phoneNumber,
        website: this.brewery.website,
        city: this.brewery.city,
        country: this.brewery.country,
        hours: this.brewery.hours,
        streetAddress1: this.brewery.streetAddress1,
        streetAddress2: this.brewery.streetAddress2,
        stateProvince: this.brewery.stateProvince,
        postalCode: this.brewery.postalCode,
        history: this.brewery.history,
        hoursOfOperation: this.brewery.hoursOfOperation,
      },
    };
  },
  methods: {
    updateBrewery() {
      breweryService.updateBrewery(this.updatedBrewery).then((response) => {
        if (response.status === 200) {
          // this.$store.commit('UPDATE_BREWERY', response.data);
          this.$store.commit("CHANGE_BREWER_PAGE_VIEW", "brewery_beer_list");
          this.$router.push({ name: "home" });
        }
      });
    },
  },
};
</script>


<style scoped>
.hidden{
  display: none;
}
.brewery-update {
  background: white;
  display: grid;
  border: 1px solid rgb(172, 13, 13);
  border-left: none;
  border-radius: 0 10px 10px 0;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: "left-form right-form";
  row-gap: 20px;
  margin-right: 40px;
  margin-bottom: 30px;
}

.left-form {
  grid-area: left-form;
  display: grid;
  justify-items: center;
  grid-template-columns: 100px 1fr;
  /* grid-template-rows: 100px 100px 100px 100px; */
  grid-template-areas:
    "name-label name"
    "type-label type"
    "phone-label phone"
    "website-label website"
    "hours-label hours-container";
  padding-left: 50px;
}

.right-form {
  grid-area: right-form;
  justify-self: "end";
  justify-items: center;
  display: grid;
  grid-template-columns: 100px 1fr;
  grid-template-rows: 100px 100px 100px 100px 100px 100px 100px;
  grid-template-areas:
    "street1-label street1"
    "street2-label street2"
    "city-label city"
    "state-label state"
    "zip-label zip"
    "history-label history"
    ". button";
}
.labels {
  padding-top: 15px;
  font-size: 15px;
  font-weight: 900;
}

#name {
  grid-area: name;
}

#name-label {
  grid-area: name-label;
}

#type {
  grid-area: type;
  
}

#type-label {
  white-space: nowrap;
  grid-area: type-label;
}

#phone {
  grid-area: phone;
}

#phone-label {
  grid-area: phone-label;
}

#website {
  grid-area: website;
}

#website-label {
  grid-area: website-label;
}

#hours-label{
  grid-area: hours-label;
}

.hours-of-operation {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas:
    "weekday open-checkbox-label open-checkbox"
    "open-label open-time open-am-pm"
    "close-label close-time close-am-pm";
  justify-content: center;
  align-items: center;
  grid-area: hours-of-operation;
  border-bottom-color: rgba(172, 13, 13, .7);
  border-bottom-width: 1px;
  border-bottom-style: solid;
  padding-bottom: 20px;
}

.day-top {
  grid-area: day-top;
}
.weekday {
  grid-area: weekday;
  justify-self: center;
  align-self: end;
  font-size: 15px;
  font-weight: 900;
}

.open-checkbox-label {
  grid-area: open-checkbox-label;
  justify-self: end;
    font-size: 15px;
  font-weight: 900;
  
}
.open-checkbox {
  grid-area: open-checkbox;
  align-self: end;
  justify-self: start;
}

.open-label {
  grid-area: open-label;
  justify-self: center;
}

.open-time {
  grid-area: open-time;
  justify-self: start;
  align-self: center;
}

.open-am-pm {
  grid-area: open-am-pm;
  align-self: center;
  justify-self: start;
}

.close-label {
  grid-area: close-label;
  align-self: center;
  justify-self: center;
    font-size: 15px;
  font-weight: 900;
}

.close-time {
  grid-area: close-time;
  align-self: center;
  justify-self: start;
}

.close-am-pm {
  grid-area: close-am-pm;
  align-self: center;
  justify-self: start;
}

.hours-container {
  grid-area: hours;
}

.hours-container {
  grid-area: hours-container;
}


#hours-of-operation-1 {
  grid-area: hours-1;
}

#hours-of-operation-2 {
  grid-area: hours-2;
}

#hours-of-operation-3 {
  grid-area: hours-3;
}

#hours-of-operation-4 {
  grid-area: hours-4;
}

#hours-of-operation-5 {
  grid-area: hours-5;
}

#hours-of-operation-6 {
  grid-area: hours-6;
}

#hours-of-operation-7 {
  grid-area: hours-7;
  border-bottom: none;
}

#street1 {
  grid-area: street1;
}

#street1-label {
  grid-area: street1-label;
}

#street2 {
  grid-area: street2;
}

#street2-label {
  grid-area: street2-label;
}

#city {
  grid-area: city;
}

#city-label {
  grid-area: city-label;
}

#state {
  grid-area: state;
}

#state-label {
  grid-area: state-label;
}

#zip {
  grid-area: zip;
}

#zip-label {
  grid-area: zip-label;
}

#history {
  grid-area: history;
    resize: none;
  border-radius: 10px;
  border: 1px solid black;
  display: flex;
  text-align: center;
}

#history-label {
  grid-area: history-label;
}

.input-field {
  padding: 10px;
  padding-left: 30px;
  padding-top: 15px;
}

#history {
  width: 400px;
  height: 80px;
}

input {
  border-radius: 7px;
  border: .5px solid black;
  margin-right: 0px;
  display: flex;
  justify-content: center;
  text-align: center;
}
button {
  margin-top: 24px;
  border-radius: 7px;
  grid-area: button;
  display: flex;
  white-space: nowrap;
  height: 40px;
  width: 200px;
  justify-content: center;
  align-items: center;
  font-weight: 700;
  cursor: pointer;

}
</style>