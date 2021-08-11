<template>
    <div id="report">
        test
        <input type="file" id="file"  ref="fileInput" />
        <button type="submit" v-on:click="submitPicture()">Submit</button>
    </div>
</template>

<script>
import PotholeService from '../services/PotholeService'
export default {    
    data: () => ({
        potholeId: this.$route.params.id,
    }),
    methods: {
        submitPicture() {
            let formData  = new FormData();
            formData.append('file', this.$refs.fileInput.files[0]);


                const options = {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }



                // console.log(schedule);

        


				PotholeService.submitPicture(formData, options, this.$route.params.id).then(response => {
                    if (response.status === 200) {
                        alert("Picture Submitted");
                    }
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
                

}
</script>

<style>
#report {
    margin-top:  10vh;
    padding: 5%;
}
</style>