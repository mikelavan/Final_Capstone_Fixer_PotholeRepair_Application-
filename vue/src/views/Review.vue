<template>
  <div class="review">
    <h1>hello pls display</h1>
    <h1>more display pls</h1>
    <h1>why isn't anything else displaying</h1>
    <div id="potholesReview" v-for="potholes in $store.state.potholes" :key="potholes">
        <ul>
            <li>hello i am testing that something is displaying on the page!</li>
            <li>something else is here in this list</li>
            <li>and another one lol</li>
            <li>{{ potholes.date_created }}</li>
        </ul>
    </div>
    
  </div>
</template>

<script>
import PotholeService from "../services/PotholeService" 
export default {
    name: "review",
    components: {},
    data: () => ({
        potholes: []
    }),
    displayReviews: function() {
            PotholeService.list().then( (response) => {
                this.$store.commit("ADD_POTHOLES", response.data);
			}).catch(error => {
				if(error.response.status == 400) {
					console.log(error.response.status);
				}
			});
        },
    methods: {
        
    }
}
</script>

<style>

</style>