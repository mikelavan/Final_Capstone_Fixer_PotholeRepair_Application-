<template>
<div>
	<div id="map" ref="map">
		
			<div id="markers" v-for="marker in $store.state.potholes" :key="marker.id">
				<map-marker :lat="marker.latitude" :lng="marker.longitude"></map-marker>
				<map-info-window :lat="marker.latitude + .00001" :lng="marker.longitude">{{marker.severity}}</map-info-window>
			</div>
			
			<!-- <map-info-window :lat="-23.344" :lng="129.036">
				<span style="background:red;">Test 2</span>
			</map-info-window>
			<map-info-window :lat="-22.344" :lng="128.036">
				<img
					src="https://1.bp.blogspot.com/_S2Wkv5XI8JI/TUL5MbRnT-I/AAAAAAAAACA/g8V_yjojceA/s1600/woot.jpg"
					height="100px"
				/>
			</map-info-window> -->
		</div>
</div>
</template>

<script>
	import MapMarker from "../components/MapMarker"
	import MapInfoWindow from "../components/MapInfoWindow"
	import PotholeService from "../services/PotholeService"

	export default {
		components: {
			MapMarker,
			MapInfoWindow
		},
		data: () => ({
			map: null,
			markers: [
				{
					latitude: -27.344,
					longitude: 133.036
				},
				{
					latitude: -26.344,
					longitude: 132.036
				},
				{
					latitude: -25.344,
					longitude: 131.036
				}
			],
		}),
		created() {
			PotholeService.list().then( (response) => {
				this.$store.commit("ADD_POTHOLES", response.data);
			}).catch(error => {
				if(error.response.status == 400) {
					console.log(error.response.status);
				}
			});
		},
		methods: {
			getMap(callback) {
				let vm = this
				function checkForMap() {
					if (vm.map) callback(vm.map)
					else setTimeout(checkForMap, 200)
				}
				checkForMap()
			},
		},
		mounted() {
			this.map = new window.google.maps.Map(this.$refs["map"], {
				center: { lat: 39.952465, lng: -75.164062 },
				zoom: 15
			})
		}
	}
</script>

<style>
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
#map {
		height: 600px;
		background: gray; 
		position: relative;
		z-index: 1;
	}
</style>