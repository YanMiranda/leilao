<template>
   <v-dialog
      persistent
      v-model="dialogDelete"
      max-width="550px"
    >
      <v-card>
        <v-card-title class="text-h5">Tem certeza que deseja apagar este item?</v-card-title>
        <v-card-actions>
          <v-btn
            text
            color="warning"
            @click="onCloseDelete"
          >
            Cancelar
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn
            text
            color="error"
            @click="onDeleteItemConfirm"
          >
            Sim, Apagar
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script>
export default {
  name: 'DeleteDialog',

  props: {
    dialogDelete: {
      type: Boolean,
      required: true,
      default: false,
    },
  },

  methods: {
    onCloseDelete() {
      this.$emit('onCancelDelete');
    },
    onDeleteItemConfirm() {
      this.$emit('onDeleteItem');
    },
  }

}
</script>

<style>

</style>