<template>
<div>
	<div id="map" ref="map">
		
			<div id="markers" v-for="marker in $store.state.potholes" :key="marker.id">
				<map-marker :lat="marker.latitude" :lng="marker.longitude"></map-marker>
				<map-info-window :lat="marker.latitude + .00001" :lng="marker.longitude">
					<img src="../../assets/640x360_placeholder.png" width="160" height="90"/><br/>
					Date Reported: {{marker.dateCreated}}<br>
					Pothole ID: {{marker.potholeId}}<br>
					<button v-on:click="deletePothole(marker.potholeId)" 
					v-show="$store.state.user.authorities.some(name => name.name === 'ROLE_ADMIN')">Delete</button>
				</map-info-window>
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

	window.createReport = function() {
		// console.log(report);
			
	
	}

	export default {
		components: {
			MapMarker,
			MapInfoWindow
		},
		data: () => ({
			map: null,
			markers: [],
			newReportMarker: {
				latitude: null,
				longitude: null,
				//img
			},
		}),
		created: function() {
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
			deletePothole(id) {
				PotholeService.deletePothole(id).then(response => {
					this.$store.commit("DELETE_POTHOLE", response);
					location.reload();
				}).catch(error => {
					if(error.response.status == 400) {
						console.log(error.response.status);
					}
				});
			},
			placeMarker(location) {
				new window.google.maps.Marker({
					position: location, 
					map: this.map
				});
			},
			createReport() {
				alert('wow 3');
				PotholeService.createReport(this.newReportMarker).then(() => {
				location.reload();
			}).catch(error => {
				if(error.response) {
					console.log('Error submitting new report.');
				} else if (error.request) {
					console.log("Error submitting new board. Server could not be reached.");
				} else {
					console.log("Error submitting new board. Request could not be created.");
				}
			})
			}
			
		},
		mounted() {
			this.map = new window.google.maps.Map(this.$refs["map"], {
				center: { lat: 39.952465, lng: -75.164062 },
				zoom: 15
			});
			let infoWindow = null;
			if(this.$store.state.user.authorities.some(name => name.name === 'ROLE_ADMIN')) {
				infoWindow = new window.google.maps.InfoWindow({
					content: "<span style='font-size: 24px;'>Click the map to report a pothole!</span>",
					position: this.map.center,
				});
			} else {
				infoWindow = new window.google.maps.InfoWindow({
					content: "<span style='font-size: 24px;'>Click the map to report a pothole! You must be logged in.</span>",
					position: this.map.center,
				});
			}
			infoWindow.open(this.map);
			this.map.addListener('click', (event) => {
				infoWindow.close();
				let loc = JSON.stringify(event.latLng.toJSON());
				//changes JSON to array to access and save in dummy var
				let locArray = JSON.parse(loc);
				this.newReportMarker = {};
				this.newReportMarker.latitude = locArray.lat;
				this.newReportMarker.longitude = locArray.lng;
				this.$store.commit('ADD_REPORT', this.newReportMarker);
				// alert(event.latLng);
				if(this.$store.state.user.authorities.some(name => name.name === 'ROLE_ADMIN')) {
					let contentString = "<span style='font-size: 24px;'>Would you like to submit this pothole report?</span><br>" + 
					"<input type='button' id='submit-report' value='Submit' onclick='createReport()'>";
					infoWindow = new window.google.maps.InfoWindow({
						content: contentString,
						position: event.latLng,
						ZIndex: 99
					});
				}
				
				infoWindow.open(this.map);
				window.google.maps.event.addListener(infoWindow, 'domready', function() {
					let submitReportBtn = document.getElementById('submit-report');
	
					submitReportBtn.addEventListener('click', () => {
						PotholeService.createReport().then(() => {
							location.reload();
						}).catch(error => {
							if(error.response) {
								console.log('Error submitting new report.');
							} else if (error.request) {
								console.log("Error submitting new board. Server could not be reached.");
							} else {
								console.log("Error submitting new board. Request could not be created.");
							}
						})
					})
				})
			});
			
			
		}
	}
</script>

<style>
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
#map {
		height: 100vh;
		background: gray; 
		position: relative;
		z-index: 1;
	}
</style>