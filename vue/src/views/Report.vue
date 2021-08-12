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
				PotholeService.submitPicture(formData, options, this.$route.params.id);
                this.$router.push("/");
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