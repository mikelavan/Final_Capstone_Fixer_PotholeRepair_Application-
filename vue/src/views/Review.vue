    <template>
  <div class="review">
    <div id="potholesReview" v-for="potholes in $store.state.potholes" :key="potholes.id">
        <ul>
            <li>Date Created: {{ potholes.dateCreated }}</li>
            <li>Latitude: {{ potholes.latitude }}</li>
            <li>Longitude: {{ potholes.longitude }}</li>
            <li>Pothole ID: {{ potholes.potholeId }}</li>
            <li>Severity: {{ potholes.severity }}</li>
            <li>Current Status: {{ potholes.status }}</li>
            <form>
                <select v-model="potholes.status" onchange="alert('test')">
                    <option v-show="potholes.status != 'Scheduled'">Scheduled</option>
                    <option v-show="potholes.status != 'Repaired'">Repaired</option>
                    <option v-show="potholes.status != 'Reported'">Reported</option>
                </select>
                <br>
                <button type="submit" onclick="return false">Submit New Status</button>
            </form>
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
        potholes: [{dateCreated: null,
                    latitude: null,
                    longitude: null,
                    potholeId: "",
                    severity: "",
                    status: null,
                   }            
                ],
                message: ""

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
        
    },
    created() {
        PotholeService.list().then( (response) => {
				this.$store.commit("ADD_POTHOLES", response.data);
			}).catch(error => {
				if(error.response.status == 400) {
					console.log(error.response.status);
				}
			});
    }, 
    mounted() {
        
    }
}
</script>

<style>
.review {
    position: absolute;
    top: 9vh;
}
@media  only screen and (max-width: 768px) {
    .review {
        top: 13vh;
    }
}
</style>