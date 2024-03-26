<template>
  <div>
    <TabelaDinamica
      title="Unidades"
      :headers="fields"
      :items="unidades"
      :pagination="pagination"
      :showbtnColumn="true"
      :showItemsPerPage="false"
      @onEdit="onEdit"
      @onDelete="onDelete"
      @onAdd="onAdd"
    />

    <CadastrarUnidade
      ref="CadastrarUnidade"
      v-if="showDialog"
      :item="selected"
      :showDialog="showDialog"
      @onCancelar="onCancelDialog"
      @onSalvar="onSaveDialog"
      @onEdit="onEditDialog"
    />

    <DialogDelete
      :dialogDelete="dialogDelete"
      @onDeleteItem="onDeleteItemConfirm"
      @onCancelDelete="dialogDelete = false"
    />
    <SnackBar ref="SnackBar"/>
  </div>
</template>

<script>
import TabelaDinamica from '@/components/table/TabelaDinamica.vue';
import { formatdatetime } from '@/util/DateUtil.js';
import CadastrarUnidade  from './FormUnidade.vue';
import DialogDelete from '@/components/table/DeleteDialog.vue';
import SnackBar from '../../components/exception/SnackBar.vue';

export default {
  name: 'Unidades',

  components: {
    TabelaDinamica,
    CadastrarUnidade,
    DialogDelete,
    SnackBar,
  },

  data() {
    return {
      formatdatetime,
      showDialog: false,
      dialogDelete: false,
      selected: null,
      unidades: [],
      fields: [
        {
          value: 'id',
          text: 'ID',
          align: 'center',
        },
        {
          value: 'nome',
          text: 'Nome',
          align: 'center',
        },
        {
          value: 'createdAt',
          text: 'Data de Criação',
          align: 'center',
        },
        {
          value: 'updatedAt',
          text: 'Data de Atualização',
          align: 'center',
        },
        {
          value: 'actions',
          text: 'Ações',
        },
      ],
      pagination: {
        sortBy: 'ID',
        page: 1,
        total: 0,
        pageCount: 1,
        itemsPerPage: 100,
      }
    };
  },

  created() {
    this.fetchUnidades();
  },

  methods: {
    fetchUnidades() {
      this.$axios.get('/unidades')
        .then(response => {
          this.unidades = response.data;
          this.pagination.total = response.data.length;
          this.formatUnidades();
        })
        .catch((error) => {
          this.$refs.SnackBar.init(error.response.data[0].mensagem, 'error');
        });
    },

    formatUnidades() {
      if (!this.unidades) return;

      this.unidades.forEach(unidade => {
        unidade.createdAt = formatdatetime(unidade.createdAt);
        unidade.updatedAt = formatdatetime(unidade.updatedAt);
      });
    },

    onEdit(item) {
      this.selected = item;
      this.showDialog = true;
    },

    onDelete(item) {
      this.selected = item;
      this.dialogDelete = true;
    },

    onCloseDelete() {
      this.dialogDelete = false;
      this.selected = null;
    },

    async onDeleteItemConfirm() {
      await this.$axios.delete(`/unidades/${this.selected.id}`)
        .then(() => {
          this.dialogDelete = false;
          this.fetchUnidades();
        })
        .catch((error) => {
          this.$refs.SnackBar.init(error.response.data[0].mensagem, 'error');
        });
    },

    onCancelDialog() {
      this.showDialog = false;
      this.selected = null;
    },

    onSaveDialog(item) {
      const sendObj = {
        ...item,
        createdAt: '',
        updatedAt: '',
      };
      this.$axios.post('/unidades', sendObj)
        .then(() => {
          this.showDialog = false;
          this.fetchUnidades();
        })
        .catch((error) => {
          this.$refs.SnackBar.init(error.response.data[0].mensagem, 'error');
        });
    },

    onEditDialog(item) {
      const sendObj = {
        ...item,
        createdAt: '',
        updatedAt: '',
      };
      this.$axios.put(`/unidades/${item.id}`, sendObj)
        .then(() => {
          this.showDialog = false;
          this.fetchUnidades();
        })
        .catch((error) => {
          this.$refs.SnackBar.init(error.response.data[0].mensagem, 'error');
        });
    },

    onAdd() {
      this.showDialog = true;
    },
  }
};
</script>
