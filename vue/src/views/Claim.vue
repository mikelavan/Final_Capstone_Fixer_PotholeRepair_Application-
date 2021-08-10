<template>
  <div>
      <form id="case">
          <br>
          <br>
          <br>
              <p> Describe the damage:</p><textarea v-model='description'></textarea><br><br>  
				<label for='name'>Name: </label><input type='text' class='claimName' v-model='name'/><br><br>
				<label for='number'>Phone Number: </label><input type='text' v-model='phoneNumber' /><br><br> 
				<label for='email'>Email: </label><input type='email' v-model='email'/><br><br> 
				<label for='date'>Date Occurred: </label> <input type='date' v-model='dateOccurred' /><br><br>
				<label for='year'> Vehicle Year: </label><input type='text' v-model='year'/><br><br>
				<label for='model'>Vehicle Model: </label><input type='text' v-model='vehicleModel'/><br><br> 
				<label for='make'>Vehicle Make: </label><input type='text' v-model='vehicleMake' /><br><br>
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
                potholeId: this.$route.params.id,
                description: null,
                name: null,
                phoneNumber: '',
                email: '',
                dateOccurred: '',
                year: '',
                vehicleModel: '',
                vehicleMake:''         
               }

},
        methods: {
            submitClaim(){
                const newClaim = {
                potholeId: this.potholeId,
                description: this.description,
                name: this.name,
                phoneNumber: this.phoneNumber,
                email: this.email,
                dateOccurred: this.dateOccurred,
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
        }
}
</script>

<style scoped>
    @import url('https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap');

    *  {  font-family: 'Raleway', sans-serif; }

    form{
        margin-top: 50px;
        padding: 5%;
        width: 90%;
    }

    form input { width: 100%; }

    textarea { width: 100%; }

</style>