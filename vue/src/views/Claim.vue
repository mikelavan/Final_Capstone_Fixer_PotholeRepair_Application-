<template>
  <div>
      <form id="case">
          <br>
          <br>
          <br>
              <p> Describe the damage:</p><textarea style='width: 99.5%;  min-height: 25%;' v-model='description'></textarea><br><br>  
				<label for='name'>Name: </label><input type='text' class='claimName' style='width: 94%;' v-model='name'/><br><br>
				<label for='number'>Phone Number: </label><input type='text' style='width:  88%;' v-model='number' /><br><br> 
				<label for='email'>Email: </label><input type='email' style='width:  94%;' v-model='email'/><br><br> 
				<label for='date'>Date Occurred: </label> <input type='date' v-model='dateOccured' /><br><br>
				<label for='year'> Vehicle Year: </label><input type='text' style='width: 89.5%;' v-model='year'/><br><br>
				<label for='model'>Vehicle Model: </label><input type='text' style='width: 88%;' v-model='vehicleModel'/><br><br> 
				<label for='make'>Vehicle Make: </label><input type='text' style='width: 88.5%;' v-model='vehicleMake' /><br><br>
				<center><button type='submit' id='claimBtn'  style='font-size: 24px;' v-on:click.prevent='submitClaim()'>Submit Claim</button></center>;
      </form>
  </div>
</template>

<script>
import PotholeService from '../services/PotholeService'
export default {
    name: 'claim',
    data() {
        return {  
                pothole_id: null,
                description: null,
                name: null,
                number: '',
                email: '',
                dateOccured: '',
                year: '',
                vehicleModel: '',
                vehicleMake:''         
               }

},
        methods: {
            submitClaim(){
                const newClaim = {
                pothole_id: this.pothole_id,
                description: this.description,
                name: this.name,
                number: this.number,
                email: this.email,
                dateOccured: this.dateOccured,
                year: this.year,
                vehicleModel: this.vehicleModel,
                vehicleMake: this.vehicleMake  
                }
                
               PotholeService.createClaim(newClaim).then(() => {
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

        computed: {
            claim() {
           const pothole_id = this.$route.params.id;
           return this.$store.state.potholes.find( pothole => {
               return pothole.id === pothole_id;
           });
       }
   }
}
</script>

<style scoped>
    form{
        margin-top: 50px;
    }
</style>