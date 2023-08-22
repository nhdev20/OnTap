<template>
 <div class= "brewery-map">
     <div id= "map">
    <iframe
        width="600"
        height="750"
        style="border:0"
        loading="lazy"
        allowfullscreen
        referrerpolicy="no-referrer-when-downgrade"
        v-bind:src="getLocationUrl">
    </iframe>
     </div>    
 </div>
</template>
<script>

export default {
    props: ['latitude', 'longitude','address'],
    computed: {
        getLocationUrl() {
            let url = `https://www.google.com/maps/embed/v1/place?key=AIzaSyB9JKrZWPm5of23KTQp4Sgky_x73EaFBDM&q=${this.getLocationSearchTerm}`;
            return url;
        },
        getLocationSearchTerm() {
            if (this.latitude == 0 && this.longitude == 0) {
                return this.address;
            } else {
                return `${this.latitude},${this.longitude}`;
            }
        }
    }
};

</script>
<style scoped>

</style>
