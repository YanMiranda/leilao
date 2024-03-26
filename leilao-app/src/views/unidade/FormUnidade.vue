<template>
  <v-dialog
    v-model="showDialog"
    justify="center"
    max-width="600px"
    persistent
  >
    <v-card class="container">
      <v-card-title>
        <span class="headline">{{ title }}</span>
      </v-card-title>
      <v-container>
          <v-text-field
            v-model="unidade.nome"
            label="Nome"
            required
          />
        <div class="d-flex justify-content-between">
          <v-text-field
            v-model="formatedDateUpdate"
            label="Data de Criação"
            readonly
            disabled
          />
          <v-spacer></v-spacer>
          <v-text-field
            v-model="formatedDateUpdate"
            label="Data de Atualização"
            readonly
            disabled
          />
        </div>
        <small v-if="!unidade.nome">*É necessário preencher o campo Nome.</small>
      </v-container>
      <v-card-actions class="mt-2" :class="(isMobile) ? 'flex-column' : ''">
        <v-btn
          rounded
          outlined
          elevation="3"
          color="error"
          :small="isMobile"
          :class="(isMobile) ? 'mb-2' : ''"
          @click="onCancelar"
        >
          <v-icon small> mdi-cancel </v-icon>
          Cancelar
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn
          rounded
          outlined
          elevation="3"
          color="success"
          :small="isMobile"
          :disabled="!valid"
          @click="onSalvar"
        >
          <v-icon small> {{ item ? 'mdi-pencil' : 'mdi-check' }}</v-icon>
          {{ item ? 'Editar' : 'Salvar' }}
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { formatdatetime } from '@/util/DateUtil.js';

export default {
  name: 'CadastrarUnidade',

  data() {
    return{
      formatdatetime,
      dialog: true,
      unidade: this.item || this.resetItem(),
    }
  },

  props: {
    item: {
      type: Object,
      default: () => {},
    },
    showDialog: {
      type: Boolean,
      default: false,
    },
  },

  computed: {
    title() {
      return this.unidade.id ? 'Editar Unidade' : 'Cadastrar Unidade';
    },

    valid() {
      return this.unidade.nome !== '';
    },

    formatedDateUpdate() {
      if (this.item) {
        return this.item.updatedAt;
      }
      return this.formatdatetime(this.unidade.createdAt);
    },

    formatedDateCreate() {
      if (this.item) {
        return this.item.createdAt;
      }
      return this.formatdatetime(this.unidade.updatedAt);
    },
  },

  methods: {
    resetItem() {
      return {
        nome: '',
        createdAt: new Date(),
        updatedAt: new Date(),
      }
    },

    onCancelar() {
      this.resetItem();
      this.$emit('onCancelar');
    },

    onSalvar() {
      if (!this.item) {
        this.resetItem();
        this.$emit('onSalvar', this.unidade);
        return;
      }
      this.resetItem();
      this.$emit('onEdit', this.unidade);
    },
  },

  created() {
    if (!this.item) {
      this.resetItem();
    }
  },
}
</script>

<style>

</style>