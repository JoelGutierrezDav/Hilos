public class HistorialFacturas {
    private int idHistorial;
    private int facturaId;
    private int clienteId;

    public HistorialFacturas(int facturaId, int clienteId, int i) {
        this.facturaId = facturaId;
        this.clienteId = clienteId;
    }


	public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }
}
