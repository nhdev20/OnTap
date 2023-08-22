<template>
  <div>
    <div ref="map" style="width: 100%; height: 500px;"></div>
  </div>
</template>

<script>
export default {
  // mounted() {
  //   this.loadGoogleMapsScript()
  //     .then(() => this.initMap())
  //     .catch(error => {
  //       console.error('Error loading Google Maps API:', error);
  //     });
  // },
  // methods: {
  //   loadGoogleMapsScript() {
  //     return new Promise((resolve, reject) => {
  //       if (window.google && window.google.maps) {
  //         // Google Maps API already loaded
  //         resolve();
  //       } else {
  //         const script = document.createElement('script');
  //         script.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyB9JKrZWPm5of23KTQp4Sgky_x73EaFBDM&libraries=places&callback=initializeMap';
  //         script.defer = true;
  //         script.onload = resolve;
  //         script.onerror = reject;
  //         document.head.appendChild(script);
  //       }
  //     });
  //   },
  //   initMap() {
  //     const mapOptions = {
  //       center: { lat: 37.7749, lng: -122.4194 }, // Coordinates for San Francisco
  //       zoom: 12
  //     };

  //     const map = new google.maps.Map(document.getElementById('map'), mapOptions);

  //     // Define marker positions and content
  //     const markerData = [
  //       { lat: 37.7749, lng: -122.4194, title: 'San Francisco' },
  //       { lat: 37.3382, lng: -121.8863, title: 'San Jose' },
  //       { lat: 34.0522, lng: -118.2437, title: 'Los Angeles' }
  //       // Add more markers as needed
  //     ];

  //     // Create and place markers on the map
  //     markerData.forEach(markerInfo => {
  //       const marker = new google.maps.Marker({
  //         position: { lat: markerInfo.lat, lng: markerInfo.lng },
  //         map,
  //         title: markerInfo.title
  //       });

  //       // Create info window for each marker
  //       const infoWindow = new google.maps.InfoWindow({
  //         content: markerInfo.title
  //       });

  //       // Show info window when marker is clicked
  //       marker.addListener('click', () => {
  //         infoWindow.open(map, marker);
  //       });
  //     });
  //   }
  // }
};
</script>

<style scoped>

</style>
